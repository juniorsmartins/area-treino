package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;

import java.math.BigDecimal;

public class EditorTestData {

    // Padrão Object Mother
//    public static Editor umEditorNovo() {
//        return new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);
//    }

    // Padrão Object Mother
//    public static Editor umEditorExistente() {
//        return new Editor(1L, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);
//    }

    // Padrões Object Mother com Builder
    public static Editor.Builder umEditorNovo() {
        return Editor.builder()
                .comNome("Kent Beck")
                .comEmail("beck@email.com")
                .comValorPagoPorPalavra(BigDecimal.TEN)
                .comPremium(true);
    }

    public static Editor.Builder umEditorExistente() {
        return umEditorNovo().comId(1L);
    }

    public static Editor.Builder umEditorComIdInexistente() {
        return umEditorNovo().comId(999L);
    }
}

