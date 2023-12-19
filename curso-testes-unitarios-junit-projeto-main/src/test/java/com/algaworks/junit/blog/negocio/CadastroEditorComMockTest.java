package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@DisplayName("Cadastro Editor com Mock")
@ExtendWith(MockitoExtension.class)
class CadastroEditorComMockTest {

    @Captor
    private ArgumentCaptor<Mensagem> mensagemArgumentCaptor;

    @Mock
    private ArmazenamentoEditor armazenamentoEditor;

    @Mock
    private GerenciadorEnvioEmail gerenciadorEnvioEmail;

    @InjectMocks
    private CadastroEditor cadastroEditor;

    @Spy
    private Editor editor = new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);

    @BeforeEach
    void init() {
//        Mockito.when(armazenamentoEditor.salvar(Mockito.any()))
//                .thenReturn(new Editor(1L, "Kent Beck", "beck@email.com", BigDecimal.TEN, true));

        Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class)))
                .thenAnswer(invocation -> {
                    var editorPassado = invocation.getArgument(0, Editor.class);
                    editorPassado.setId(1L);
                    return editorPassado;
                });
    }

    @Nested
    @DisplayName("Método CadastrarEditor")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class MetodoCadastrarEditor {

        @Test
        @Order(1)
        @DisplayName("cadastrarEditor - salvar novo editor.")
        void dadoEditorValido_QuandoCriar_EntaoRetornarEditorSalvo() {
            var editorSalvo = cadastroEditor.criar(editor);
            Assertions.assertEquals(1L, editorSalvo.getId());
        }

        @Test
        @Order(2)
        @DisplayName("cadastrarEditor - chamar uma vez salvar de armazenamento.")
        void dadoEditorValido_QuandoCriar_EntaoDeveChamarMetodoSalvarDoArmazenamento() {
            cadastroEditor.criar(editor);
            Mockito.verify(armazenamentoEditor, Mockito.times(1))
                    .salvar(Mockito.any(Editor.class));
        }

        @Test
        @Order(3)
        @DisplayName("cadastrarEditor - não enviar email quando lançar exception.")
        void dadoEditorValido_QuandoCriarComExcecaoForcada_EntaoNaoEnviarEmailAoLancarException() {
            Mockito.when(armazenamentoEditor.salvar(editor)).thenThrow(new RuntimeException());
            Executable acao = () -> cadastroEditor.criar(editor);
            Assertions.assertThrows(RuntimeException.class, acao);
            Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviarEmail(Mockito.any());
        }

        @Test
        @Order(4)
        @DisplayName("cadastrarEditor - enviar email para o editor.")
        void dadoEditorValido_QuandoCriar_EntaoEnviarEmailComDestinoAoEditor() {
            var editorSalvo = cadastroEditor.criar(editor);
            Mockito.verify(gerenciadorEnvioEmail).enviarEmail(mensagemArgumentCaptor.capture());
            var mensagemPassada = mensagemArgumentCaptor.getValue();
            Assertions.assertEquals(editorSalvo.getEmail(), mensagemPassada.getDestinatario());
        }

        @Test
        @Order(5)
        @DisplayName("cadastrarEditor - verificar unicidade de email.")
        void dadoEditorValido_QuandoCriar_EntaoVerificarSeEmailEhUnico() {
//            var editorSpy = Mockito.spy(editor);
            cadastroEditor.criar(editor);
            Mockito.verify(editor, Mockito.atLeast(1)).getEmail(); // Verifica se foi chamado ao menos 1 vez
        }

        @Test
        @Order(6)
        @DisplayName("cadastrarEditor - garantir retorno para duas ações de criar e lançar exception na segunda.")
        void dadoEditorComEmailExistente_QuandoCriar_EntaoLancarException() {
            Mockito.when(armazenamentoEditor.encontrarPorEmail("beck@email.com"))
                    .thenReturn(Optional.empty())
                    .thenReturn(Optional.of(editor));

            var editorComEmailExistente = new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);
            cadastroEditor.criar(editor);
            Executable acao = () -> cadastroEditor.criar(editorComEmailExistente);
            Assertions.assertThrows(RegraNegocioException.class, acao);
        }

        @Test
        @Order(7)
        @DisplayName("cadastrarEditor - enviar email apenas após salvar (garantir ordem dos métodos).")
        void dadoEditorValido_QuandoCriar_EntaoVerificarEnvioDeEmailPosteriorAoSalvar() {
            cadastroEditor.criar(editor);
            var inOrder = Mockito.inOrder(armazenamentoEditor, gerenciadorEnvioEmail);
            inOrder.verify(armazenamentoEditor, Mockito.times(1)).salvar(editor);
            inOrder.verify(gerenciadorEnvioEmail, Mockito.times(1)).enviarEmail(Mockito.any());
        }
    }
}

