package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
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

    private CadastroEditor cadastroEditor;

    private Editor editor;

    @BeforeEach
    void init() {
        editor = new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);

        Mockito.when(armazenamentoEditor.salvar(Mockito.any()))
                .thenReturn(new Editor(1L, "Kent Beck", "beck@email.com", BigDecimal.TEN, true));

        cadastroEditor = new CadastroEditor(armazenamentoEditor, gerenciadorEnvioEmail);
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
    }
}

