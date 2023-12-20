package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;

import java.math.BigDecimal;

public class EditorTestDataObjectMother {

    public static Editor umEditorNovo() {
        return new Editor(null, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);
    }

    public static Editor umEditorExistente() {
        return new Editor(1L, "Kent Beck", "beck@email.com", BigDecimal.TEN, true);
    }
}

