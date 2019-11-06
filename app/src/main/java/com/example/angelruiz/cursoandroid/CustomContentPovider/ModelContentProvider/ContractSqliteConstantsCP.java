package com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContractSqliteConstantsCP {
    public ContractSqliteConstantsCP() {
    }

    //content authority
    public static final String CONTENT_AUTHORITY = "com.example.angelruiz.cursoandroid";

    //base content authority
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //name path(name db)
    public static final String PATCH_WEIGHT = "registropeso";


    //BaseColumns provides a id(primarykey) for the tabla and a accountant of raws(regiters) it works with CursorAdapter
    public static final class ConstantsSqliteDB implements BaseColumns {

        //content Uri, for access to the data to the provider weight
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATCH_WEIGHT);
        //DB
        public static final String NAME_DATABASE_CP = "registropeso";
        public static final int VERSION_DATABASE = 1;
        //TABLE 0
        public static final String NAME_TABLE = "pesopersona";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PESO = "peso";
        public static final String COLUMN_DATE = "fecha";
        public static final String _COUNT = BaseColumns._COUNT;
    }
}
