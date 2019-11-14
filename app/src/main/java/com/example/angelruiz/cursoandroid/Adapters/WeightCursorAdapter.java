package com.example.angelruiz.cursoandroid.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.angelruiz.cursoandroid.CustomContentPovider.ModelContentProvider.ContractSqliteConstantsCP;
import com.example.angelruiz.cursoandroid.R;

public class WeightCursorAdapter extends CursorAdapter {

    public WeightCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.view_inflate_lv_content_provider, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvShowWeight = view.findViewById(R.id.tvShowWeight);
        TextView tvShowDate = view.findViewById(R.id.tvShowDate);

        int weightColumnIndex = cursor.getColumnIndex(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_PESO);
        int dateColumnIndex = cursor.getColumnIndex(ContractSqliteConstantsCP.ConstantsSqliteDB.COLUMN_DATE);

        String weight = cursor.getString(weightColumnIndex);
        String date = cursor.getString(dateColumnIndex);
        String weightKg = weight + " Kg";

        tvShowWeight.setText(weightKg);
        tvShowDate.setText(date);
    }
}
