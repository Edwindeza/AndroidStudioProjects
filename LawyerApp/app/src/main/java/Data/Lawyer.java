package Data;

import android.content.ContentValues;

import java.util.UUID;

/**
 * Created by Dise07 on 20/09/2016.
 *
 */

public class Lawyer {
    private String id;
    private String name;
    private String specialty;
    private String phoneNumber;
    private String bio;
    private String avatarUri;

    public Lawyer(String name, String specialty, String phoneNumber, String bio, String avatarUri) {
        this.id = UUID.randomUUID().toString();
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.specialty=specialty;
        this.bio=bio;
        this.avatarUri=avatarUri;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(LawyersContract.LawyerEntry.ID, id);
        values.put(LawyersContract.LawyerEntry.NAME, name);
        values.put(LawyersContract.LawyerEntry.SPECIALTY, specialty);
        values.put(LawyersContract.LawyerEntry.PHONE_NUMBER, phoneNumber);
        values.put(LawyersContract.LawyerEntry.BIO, bio);
        values.put(LawyersContract.LawyerEntry.AVATAR_URI, avatarUri);
        return values;
    }
}
