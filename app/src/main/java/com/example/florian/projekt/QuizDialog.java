package com.example.florian.projekt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

import java.net.URL;

/**
 * Created by patricia on 29.03.18.
 */

public class QuizDialog extends DialogFragment {
    Resources res = getResources();
    private final String[] URLs = res.getStringArray(R.array.QuizIndex);
    private String selectedURL = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setSingleChoiceItems(R.array.QuizIndexNames, 0,
                null)
                .setTitle("Wähle ein Quiz, das du downloaden möchtest")
                .setPositiveButton("Auswählen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        selectedURL = URLs[selectedPosition];
                        AddNewQuiz.setURL(selectedURL);
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}



