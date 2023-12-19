package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@DisplayName("Cadastro Editor com Mock")
@ExtendWith(MockitoExtension.class)
class CadastroEditorComMockTest {

    @Mock
    private ArmazenamentoEditor armazenamentoEditor;

    @Mock
    private GerenciadorEnvioEmail gerenciadorEnvioEmail;

    @InjectMocks
    private CadastroEditor cadastroEditor;

    private Editor editor;

    @BeforeEach
    void init() {
        editor = new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);

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
        @DisplayName("cadastrarEditor - lançar exceção e não enviar email.")
        void dadoEditorValido_QuandoCriar_EntaoLancarExceptionAndNaoEnviarEmail() {
            Mockito.when(armazenamentoEditor.salvar(editor)).thenThrow(new RuntimeException());
            Executable acao = () -> cadastroEditor.criar(editor);
            Assertions.assertThrows(RuntimeException.class, acao);
            Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviarEmail(Mockito.any());
        }
    }
}

