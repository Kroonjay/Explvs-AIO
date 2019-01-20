package org.aio.gui.fields;

import org.aio.gui.utils.NumberDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberField extends JTextField {
    private int minValue = -1;
    private int maxValue = -1;

    public NumberField() {
        ((AbstractDocument) getDocument()).setDocumentFilter(new NumberDocumentFilter());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                validateField();
            }
        });

        setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent input) {
                return validateField();
            }
        });
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    private boolean validateField() {
        if (getText().trim().isEmpty()) {
            setBorder(BorderFactory.createLineBorder(Color.RED));
            return false;
        }

        int value = Integer.parseInt(getText().trim());

        if (minValue != -1 && value < minValue) {
            setBorder(BorderFactory.createLineBorder(Color.RED));
            return false;
        }

        if (maxValue != -1 && value > maxValue) {
            setBorder(BorderFactory.createLineBorder(Color.RED));
            return false;
        }

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return true;
    }
}
