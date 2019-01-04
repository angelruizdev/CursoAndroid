package com.example.angelruiz.cursoandroid.Interfaces;

import com.example.angelruiz.cursoandroid.Arrays.ArrayProductosBD;

public interface InterfComunicaFgmtDetBDyProBD {//esta interface permite mandar datos entre fragments
    public void enviarDatos(ArrayProductosBD arrayProductosBD);//metodo a implementar en activity donde viven los fragment, para comunicarlos,(pasar datos de un frgmnt a otro)
}
