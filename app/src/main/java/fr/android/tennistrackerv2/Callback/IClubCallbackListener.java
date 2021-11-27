package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Club;


/**
 * Interface IClubCallbackListener
 * Les APIs Firebase sont asynchrones, c'est pour ceci que nous avons pas besoin de Thread pour modifier l'interface utilisateur.
 * Les données sont retournées directement avant que le résultat de la requête soit complète,
 * c'est pour ceci qu'une simple méthode ne peut pas retourner directement les données
 * Il est nécessaire d'utiliser une methode de callback qui va attendre les resultats de la requête puis modifier notre interface.
 */


public interface IClubCallbackListener {

    void onClubLoadSuccess(List<Club> clubList);
    void onClubLoadFailed(String messageError);

}
