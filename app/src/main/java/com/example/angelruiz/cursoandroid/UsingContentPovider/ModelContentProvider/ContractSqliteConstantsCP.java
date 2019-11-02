package com.example.angelruiz.cursoandroid.UsingContentPovider.ModelContentProvider;

import android.provider.BaseColumns;

public class ContractSqliteConstantsCP {
    public ContractSqliteConstantsCP() {
    }

    //BaseColumns provides a id(primarykey) for the tabla and a accountant of raws(regiters) it works with CursorAdapter
    public static final class ConstantsSqliteDB implements BaseColumns {
        //DB
        public static final String NAME_DATABASE_CP = "registropeso";
        public static final int VERSION_DATABASE = 1;
        //TABLE 0
        public static final String NAME_TABLE = "";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PESO = "";
        public static final String COLUMN_DATE = "";
        public static final String _COUNT = BaseColumns._COUNT;
    }
}
