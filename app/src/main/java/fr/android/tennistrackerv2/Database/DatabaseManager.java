package fr.android.tennistrackerv2.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.ClubStats;
import fr.android.tennistrackerv2.Model.Statistique;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tennisTracker.db";

    //Club stats table
    private static final String CLUB_STATS_TABLE_NAME = "club_stats";
    private static final String CLUB_STATS_ID = "id_club";
    private static final String NAME_CLUB = "nameClub";
    private static final String IMG_CLUB = "imgClub";
    // Stats table
    private static final String STATS_TABLE_NAME = "stats";
    private static final String STATS_ID = "id_stats";
    private static final String TIR_COL = "tir";
    private static final String TIRCADRE_COL = "tirCadre";
    private static final String BUT_COL = "but";
    private static final String PASSE_COL = "passe";
    private static final String HORSJEU_COL = "horsJeu";
    private static final String FAUTE_COL = "faute";
    private static final String CARTONJ_COL = "cartonJaune";
    private static final String CARTONR_COL = "cartonRouge";


    private static final String CLUB_STATS_TABLE_CREATE = "create table "
            + CLUB_STATS_TABLE_NAME + " ("
            + CLUB_STATS_ID + " integer primary key autoincrement, "
            + NAME_CLUB + " text not null, "
            + IMG_CLUB + " text not null, "
            + STATS_ID + " integer,"
            + " FOREIGN KEY ("+STATS_ID+") REFERENCES "+STATS_TABLE_NAME+"("+STATS_ID+"));";

    private static final String STATS_TABLE = "create table "
            + STATS_TABLE_NAME + " ("
            + STATS_ID + " integer primary key autoincrement, "
            + TIR_COL + " integer, "
            + TIRCADRE_COL + " integer, "
            + BUT_COL + " integer, "
            + PASSE_COL + " integer,"
            + HORSJEU_COL + " integer,"
            + FAUTE_COL + " integer,"
            + CARTONJ_COL + " integer,"
            + CARTONR_COL + " integer)";


    public DatabaseManager(Context context){
        super(context, DATABASE_NAME,null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(STATS_TABLE);
        sqLiteDatabase.execSQL(CLUB_STATS_TABLE_CREATE);

        String INSERT_STATS1 = "INSERT INTO " + STATS_TABLE_NAME + "(" +TIR_COL+"," + TIRCADRE_COL + ","+ BUT_COL + ","+ PASSE_COL + ","+ HORSJEU_COL + ","+ FAUTE_COL + ","+ CARTONJ_COL + ","+ CARTONR_COL+ ")" +
                "VALUES (0, 0, 0, 0, 0, 0, 0, 0)";

        String INSERT_STATS2 = "INSERT INTO " + STATS_TABLE_NAME + "(" +TIR_COL+"," + TIRCADRE_COL + ","+ BUT_COL + ","+ PASSE_COL + ","+ HORSJEU_COL + ","+ FAUTE_COL + ","+ CARTONJ_COL + ","+ CARTONR_COL+ ")" +
                "VALUES (0, 0, 0, 0, 0, 0, 0, 0)";

        String INSERT_STATS3 = "INSERT INTO " + STATS_TABLE_NAME + "(" +TIR_COL+"," + TIRCADRE_COL + ","+ BUT_COL + ","+ PASSE_COL + ","+ HORSJEU_COL + ","+ FAUTE_COL + ","+ CARTONJ_COL + ","+ CARTONR_COL+ ")" +
                "VALUES (0, 0, 0, 0, 0, 0, 0, 0)";

        String INSERT_STATS4 = "INSERT INTO " + STATS_TABLE_NAME + "(" +TIR_COL+"," + TIRCADRE_COL + ","+ BUT_COL + ","+ PASSE_COL + ","+ HORSJEU_COL + ","+ FAUTE_COL + ","+ CARTONJ_COL + ","+ CARTONR_COL+ ")" +
                "VALUES (0, 0, 0, 0, 0, 0, 0, 0)";

        String INSERT_STATS5 = "INSERT INTO " + STATS_TABLE_NAME + "(" +TIR_COL+"," + TIRCADRE_COL + ","+ BUT_COL + ","+ PASSE_COL + ","+ HORSJEU_COL + ","+ FAUTE_COL + ","+ CARTONJ_COL + ","+ CARTONR_COL+ ")" +
                "VALUES (0, 0, 0, 0, 0, 0, 0, 0)";

        String INSERT_STATS6 = "INSERT INTO " + STATS_TABLE_NAME + "(" +TIR_COL+"," + TIRCADRE_COL + ","+ BUT_COL + ","+ PASSE_COL + ","+ HORSJEU_COL + ","+ FAUTE_COL + ","+ CARTONJ_COL + ","+ CARTONR_COL+ ")" +
                "VALUES (0, 0, 0, 0, 0, 0, 0, 0)";

        String INSERT_DATA_CLUB1 =
                "INSERT INTO " + CLUB_STATS_TABLE_NAME +  " ("+ NAME_CLUB + "," + IMG_CLUB + "," +  STATS_ID +") " +
                        "VALUES ('Paris Saint-Germain','https://ssl.gstatic.com/onebox/media/sports/logos/mcpMspef1hwHwi9qrfp4YQ_48x48.png', 1);";
        String INSERT_DATA_CLUB2 =
                "INSERT INTO " + CLUB_STATS_TABLE_NAME +  " ("+ NAME_CLUB + "," + IMG_CLUB + "," +  STATS_ID +") " +
                        "VALUES ('Lens','https://ssl.gstatic.com/onebox/media/sports/logos/TUvwItKazVFpgThEhhlN1Q_48x48.png', 2);";
        String INSERT_DATA_CLUB3 =
                "INSERT INTO " + CLUB_STATS_TABLE_NAME +  " ("+ NAME_CLUB + "," + IMG_CLUB + "," +  STATS_ID +") " +
                        "VALUES ('Olympique de Marseille','https://ssl.gstatic.com/onebox/media/sports/logos/KfBX1kHNj26r9NxpqNaTkA_48x48.png', 3);";
        String INSERT_DATA_CLUB4 =
                "INSERT INTO " + CLUB_STATS_TABLE_NAME +  " ("+ NAME_CLUB + "," + IMG_CLUB + "," +  STATS_ID +") " +
                        "VALUES ('Angers','https://ssl.gstatic.com/onebox/media/sports/logos/Be-wAQaZCDYKCGls1191zg_48x48.png', 4);";

        String INSERT_DATA_CLUB5 =
                "INSERT INTO " + CLUB_STATS_TABLE_NAME +  " ("+ NAME_CLUB + "," + IMG_CLUB + "," +  STATS_ID +") " +
                        "VALUES ('Nice','https://ssl.gstatic.com/onebox/media/sports/logos/Llrxrqsc3Tw4JzE6xM7GWw_48x48.png', 5);";

        String INSERT_DATA_CLUB6 =
                "INSERT INTO " + CLUB_STATS_TABLE_NAME +  " ( "+ NAME_CLUB + "," + IMG_CLUB + "," +  STATS_ID +" ) " +
                        "VALUES ('Olympique Lyonnais','https://ssl.gstatic.com/onebox/media/sports/logos/SrKK55dUkCxe4mJsyshfCg_48x48.png', 6);";

        sqLiteDatabase.execSQL(INSERT_STATS1);
        sqLiteDatabase.execSQL(INSERT_STATS2);
        sqLiteDatabase.execSQL(INSERT_STATS3);
        sqLiteDatabase.execSQL(INSERT_STATS4);
        sqLiteDatabase.execSQL(INSERT_STATS5);
        sqLiteDatabase.execSQL(INSERT_STATS6);

        sqLiteDatabase.execSQL(INSERT_DATA_CLUB1);
        sqLiteDatabase.execSQL(INSERT_DATA_CLUB2);
        sqLiteDatabase.execSQL(INSERT_DATA_CLUB3);
        sqLiteDatabase.execSQL(INSERT_DATA_CLUB4);
        sqLiteDatabase.execSQL(INSERT_DATA_CLUB5);
        sqLiteDatabase.execSQL(INSERT_DATA_CLUB6);






    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CLUB_STATS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STATS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @SuppressLint("Range")
    public ArrayList<ClubStats> getClubStats() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<ClubStats> clubs = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + CLUB_STATS_TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ClubStats clubStats = new ClubStats(cursor.getInt(cursor.getColumnIndex("id_club")),cursor.getString(cursor.getColumnIndex("nameClub")), cursor.getString(cursor.getColumnIndex("imgClub")), cursor.getInt(cursor.getColumnIndex("id_stats")));
            clubs.add(clubStats);
            cursor.moveToNext();
        }
        return clubs;
    }

    @SuppressLint("Range")
    public Statistique getStatistiqueClubById(int id_stats) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Statistique statistique = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + STATS_TABLE_NAME + " WHERE id_stats = "+ id_stats , null);
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                int stats_id = cursor.getInt(cursor.getColumnIndex("id_stats"));
                int tir = cursor.getInt(cursor.getColumnIndex("tir"));
                int tirCadre = cursor.getInt(cursor.getColumnIndex("tirCadre"));
                int but = cursor.getInt(cursor.getColumnIndex("but"));
                int passe = cursor.getInt(cursor.getColumnIndex("passe"));
                int horsJeu = cursor.getInt(cursor.getColumnIndex("horsJeu"));
                int faute = cursor.getInt(cursor.getColumnIndex("faute"));
                int cartonJ = cursor.getInt(cursor.getColumnIndex("cartonJaune"));
                int cartonR = cursor.getInt(cursor.getColumnIndex("cartonRouge"));
                statistique = new Statistique(stats_id,tir, tirCadre, but, faute, cartonJ, cartonR, passe, horsJeu, 0);
            }
            cursor.close();
        }
        return statistique;
    }

    public void updateStatsClub(String nameClub, int tir, int tirCadre, int but, int passe, int horsJeu, int faute, int cartonJ, int cartonR) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int id;
        switch (nameClub) {
            case "Paris Saint-Germain":
                id = 1;
                break;
            case "Lens":
                id = 2;
                break;
            case "Olympique de Marseille":
                id = 3;
                break;
            case "Angers":
                id = 4;
                break;
            case "Nice":
                id = 5;
                break;
            default:
                id = 6;
                break;
        }

        String query = "UPDATE " + STATS_TABLE_NAME + " SET "
                + TIR_COL + " = " + TIR_COL +" + " + tir + ", " + TIRCADRE_COL + " = " + TIRCADRE_COL +" + "+ tirCadre
                + "," + BUT_COL + "=" + BUT_COL + "+" + but +"," + PASSE_COL + "= " + PASSE_COL +"+"
                + passe +"," + HORSJEU_COL + "=" + HORSJEU_COL + "+" + horsJeu +"," + FAUTE_COL +" = " + FAUTE_COL + "+" + faute + ", " + CARTONJ_COL +" = "
                + CARTONJ_COL + "+" + cartonJ + ", " +CARTONR_COL +"=" +CARTONR_COL + "+" +cartonR +" WHERE id_stats = " + id;
        sqLiteDatabase.execSQL(query);
    }
}
