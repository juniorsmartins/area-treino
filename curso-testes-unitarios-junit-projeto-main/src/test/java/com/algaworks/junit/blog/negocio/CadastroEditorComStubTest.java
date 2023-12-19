package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

@DisplayName("Cadastro de Editor com Stub")
class CadastroEditorComStubTest {

    private CadastroEditor cadastroEditor;

    private ArmazenamentoEditorFixoEmMemoria armazenamentoEditorFixoEmMemoria;

    private Editor editor;

    @BeforeEach
    void criarCenarioLocal() {
        armazenamentoEditorFixoEmMemoria = new ArmazenamentoEditorFixoEmMemoria();

        cadastroEditor = new CadastroEditor(armazenamentoEditorFixoEmMemoria,
                new GerenciadorEnvioEmail() {
                    @Override
                    public void enviarEmail(Mensagem mensagem) {
                        System.out.println("\n\nEnviando mensagem por email para: \n" + mensagem.getDestinatario());
                    }
                });

        editor = new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);
    }

    @Nested
    @DisplayName("Método Criar")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class MetodoCriar {

        @Test
        @Order(1)
        @DisplayName("criar - salvar editor.")
        void dadoEditorValido_QuandoCriar_EntaoDeveRetornarIdDeCadastro() {
            cadastroEditor.criar(editor);
            Assertions.assertEquals(1, editor.getId());
            Assertions.assertTrue(armazenamentoEditorFixoEmMemoria.chamouSalvar);
        }

        @Test
        @Order(2)
        @DisplayName("criar - lançar exceção por editor nulo.")
        void dadoEditorNulo_QuandoCriar_EntaoLancarNullPointerException() {
            Executable acao = () -> cadastroEditor.criar(null);
            Assertions.assertThrows(NullPointerException.class, acao);
            Assertions.assertFalse(armazenamentoEditorFixoEmMemoria.chamouSalvar);
        }

        @Test
        @Order(3)
        @DisplayName("criar - lançar exceção quando email existir.")
        void dadoEditorValido_QuandoCriarComEmailExistente_EntaoDeveLancarException() {
            var editorComEmailExistente = new Editor(2L, "Robert Martin", "bob@email.com", BigDecimal.TEN, true);
            Executable acao = () -> cadastroEditor.criar(editorComEmailExistente);
            Assertions.assertThrows(RegraNegocioException.class, acao);
            Assertions.assertFalse(armazenamentoEditorFixoEmMemoria.chamouSalvar);
        }
    }

//    @Nested
//    @DisplayName("Método EncontrarPorEmail")
//    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//    class EncontrarPorEmail {
//
//    }
}

