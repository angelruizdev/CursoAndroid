package com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContractSqliteConstantsCP {
    public ContractSqliteConstantsCP() {
    }

    //content authority(our package)
    public static final String CONTENT_AUTHORITY = "com.example.angelruiz.cursoandroid";
    //base content authority(create Uri)
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //name path(name db)
    public static final String PATCH_WEIGHT = "registropeso";

    //BaseColumns provides a id(primarykey) for the tabla and a accountant of raws(regiters) it works with CursorAdapter
    public static final class ConstantsSqliteDB implements BaseColumns {

        //types mime
        public static final String CONTENT_DIR_TYPE =
                                   ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATCH_WEIGHT;

        public static final String CONTENT_ITEM_TYPE =
                                   ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATCH_WEIGHT;

        //content Uri, for access to the data to the provider weight(create a new Uri with uri base and path of db)
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
