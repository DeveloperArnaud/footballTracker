package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Format;
import fr.android.tennistrackerv2.Model.FormatLastMatch;

public interface IFormatLastMatchCallbackListener {

    void onFormatLastMatchLoadSuccess(List<FormatLastMatch> formatList);
    void onFormatLastMatchLoadFailed(String message);
}
