package com.example.habitstracker.presentation.home.habits

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.domain.Habit
import com.example.habitstracker.R

class DeleteItemDialogFragment(
    private val listener: DeleteItemListener,
    private val habit: Habit
): DialogFragment() {

    interface DeleteItemListener {
        fun confirmButtonClicked(habit: Habit)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context)
            .setMessage(R.string.dialog_delete_habit_message)
            .setPositiveButton(R.string.dialog_delete_habit_positive_button) { _, _ ->
                listener.confirmButtonClicked(habit)
            }
            .setNegativeButton(R.string.dialog_delete_habit_negative_button) { dialog, _ ->
                dialog.cancel()
            }
            .create()
    }

    companion object {

        const val TAG = "DeleteItemDialog"

    }
}