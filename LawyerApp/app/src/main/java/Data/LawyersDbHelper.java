package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dise07 on 20/09/2016.
 */

class LawyersDbHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "Lawyer.db";

    public LawyersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("CREATE TABLE " + LawyersContract.LawyerEntry.TABLE_NAME + " ("
                + LawyersContract.LawyerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LawyersContract.LawyerEntry.ID + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.NAME + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.SPECIALTY + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.PHONE_NUMBER + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.BIO + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.AVATAR_URI + " TEXT,"
                + "UNIQUE (" + LawyersContract.LawyerEntry.ID + "))");

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table...

        // Contenedor de valores
        ContentValues values = new ContentValues();

        // Pares clave-valor
        values.put(LawyersContract.LawyerEntry.ID, "L-001");
        values.put(LawyersContract.LawyerEntry.NAME, "Carlos solarte");
        values.put(LawyersContract.LawyerEntry.SPECIALTY, "Abogado penalista");
        values.put(LawyersContract.LawyerEntry.PHONE_NUMBER, "300 200 1111");
        values.put(LawyersContract.LawyerEntry.BIO, "Carlos es una profesional con 5 años de trayectoria...");
        values.put(LawyersContract.LawyerEntry.AVATAR_URI, "carlos_solarte.jpg");

        // Insertar...
        mockData(db);
        //db.insert(LawyersContract.LawyerEntry.TABLE_NAME, null, values);
    }
    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockLawyer(sqLiteDatabase, new Lawyer("Carlos Perez", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "carlos_perez.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Daniel Samper", "Abogado accidentes de tráfico",
                "300 200 2222", "Gran profesional con experiencia de 5 años en accidentes de tráfico.",
                "daniel_samper.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Lucia Aristizabal", "Abogado de derechos laborales",
                "300 200 3333", "Gran profesional con más de 3 años de experiencia en defensa de los trabajadores.",
                "lucia_aristizabal.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Marina Acosta", "Abogado de familia",
                "300 200 4444", "Gran profesional con experiencia de 5 años en casos de familia.",
                "marina_acosta.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Olga Ortiz", "Abogado de administración pública",
                "300 200 5555", "Gran profesional con experiencia de 5 años en casos en expedientes de urbanismo.",
                "olga_ortiz.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Pamela Briger", "Abogado fiscalista",
                "300 200 6666", "Gran profesional con experiencia de 5 años en casos de derecho financiero",
                "pamela_briger.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Rodrigo Benavidez", "Abogado Mercantilista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en redacción de contratos mercantiles",
                "rodrigo_benavidez.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Tom Bonz", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "tom_bonz.jpg"));
    }

    public long mockLawyer(SQLiteDatabase db, Lawyer lawyer) {
        return db.insert(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                lawyer.toContentValues());
    }
    public long saveLawyer(Lawyer lawyer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                lawyer.toContentValues());

    }



}
