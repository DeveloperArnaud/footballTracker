package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Format;

/**
 * Interface IFormatCallbackListener
 * Why do we need callback listerner ?
 * Firebase APIs are asynchronous, meaning that they return immediately, before the results of your query are complete.
 * Some time later, your callback will be invoked with the results.
 * This means that our formatList is returning whatever is in the array majors, before the query finishes.
 *
 * You'll need to correctly handle the asynchronous nature of Firebase APIs,
 * which means that you can't write methods that directly return data that you fetch from the database.
 * You'll have to use the callbacks to wait for results, then update your UI as needed.
 */

public interface IFormatCallbackListener {

    void onFormatLoadSuccess(List<Format> formatList);
    void onFormatLoadFailed(String message);
}
