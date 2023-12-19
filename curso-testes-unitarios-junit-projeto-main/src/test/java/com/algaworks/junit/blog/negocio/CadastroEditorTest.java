package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

@DisplayName("Cadastro de Editor")
class CadastroEditorTest {

    static CadastroEditor cadastroEditor;

    static ArmazenamentoEditorFixoEmMemoria armazenamentoEditorFixoEmMemoria;

    private Editor editor;

    @BeforeAll
    static void criarCenarioGlobal() {
        armazenamentoEditorFixoEmMemoria = new ArmazenamentoEditorFixoEmMemoria();

        cadastroEditor = new CadastroEditor(armazenamentoEditorFixoEmMemoria,
                new GerenciadorEnvioEmail() {
                    @Override
                    public void enviarEmail(Mensagem mensagem) {
                        System.out.println("\n\nEnviando mensagem por email para: \n" + mensagem.getDestinatario());
                    }
                });
    }

    @BeforeEach
    void criarCenarioLocal() {
        editor = new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);
    }

    @Nested
    @DisplayName("Método Criar")
    class MetodoCriar {

        @Test
        @DisplayName("criar - salvar editor.")
        void dadoEditorValido_QuandoCriar_EntaoDeveRetornarIdDeCadastro() {
            cadastroEditor.criar(editor);
            Assertions.assertEquals(1, editor.getId());
            Assertions.assertTrue(armazenamentoEditorFixoEmMemoria.chamouSalvar);
        }

        @Test
        @DisplayName("criar - lançar exceção por editor nulo.")
        void dadoEditorNulo_QuandoCriar_EntaoLancarNullPointerException() {
            Executable acao = () -> cadastroEditor.criar(null);
            Assertions.assertThrows(NullPointerException.class, acao);
        }
    }
}

