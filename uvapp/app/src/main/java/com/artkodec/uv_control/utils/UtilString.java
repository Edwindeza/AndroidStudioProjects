package com.artkodec.uv_control.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by manu on 11/02/16.
 */
public class UtilString {
    public static String capitalizeFirstLetter(String original) {
        char[] chars = original.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static String readFromAssets(Context context, String filename) throws IOException {
        StringBuilder buf=new StringBuilder();
        InputStream file= context.getAssets().open(filename);
        BufferedReader in=
                new BufferedReader(new InputStreamReader(file));
        String str;


        while ((str=in.readLine()) != null) {
            buf.append(str+"\n\n");
        }
        in.close();
        return buf.toString();
    }
}
