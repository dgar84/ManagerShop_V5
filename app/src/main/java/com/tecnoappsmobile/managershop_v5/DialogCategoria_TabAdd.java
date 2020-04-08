package com.tecnoappsmobile.managershop_v5;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by DANIEL on 16/10/2016.
 */

public class DialogCategoria_TabAdd extends Fragment implements View.OnClickListener {

    private Button btnAddCategoria,btnGuardarDialog,btnVolver,btnBorrarCategoria,btnVolverR;
    private EditText editCategoria;
    private ArrayList<String> categorias=new ArrayList<String>();
    private LinearLayout layoutAddCat,layoutEditCat;

    private int idIntCategoria; // Para saber id de la posición del ReciclerView

    private String categoriaMod; // Es el nombre de la categoria elegida en el recyclerView

    Dialog dialogWarning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.dialog_tab_add_categoria,container,false);

        btnAddCategoria=(Button) v.findViewById(R.id.btnAddCategoria);
        btnAddCategoria.setOnClickListener(this);

        btnVolver=(Button) v.findViewById(R.id.btnVolverCategoria);
        btnVolver.setOnClickListener(this);

        btnVolverR=(Button)v.findViewById(R.id.btnVolverCategoriaR);
        btnVolverR.setOnClickListener(this);

        btnGuardarDialog=(Button) v.findViewById(R.id.btnGuardarDialogCategoria);
        btnGuardarDialog.setOnClickListener(this);

        btnBorrarCategoria=(Button) v.findViewById(R.id.btnBorrarCategoria);
        btnBorrarCategoria.setOnClickListener(this);

        editCategoria=(EditText) v.findViewById(R.id.edAddCategoria);
     /////   Bundle bundle_ft1=getArguments();
       // bundle_ft1.getString("categoria");
     /////   editCategoria.setText(bundle_ft1.getString("categoria"));
      ///  Bundle bundle=getArguments();

        //here is your list array
/////////////        categorias=this.getArguments().getStringArrayList("listCategoria");


     //  DialogCategoriaAc act=(DialogCategoriaAc) getActivity();
     //   categorias=act.getCategoria();
        categorias=InitActivity.productos.sacarCategorias();

        /* Creamos los LinealLayout para hacer visble los botones según el caso:
            1) Inicialmente en el Tab Add/Edit categoría se habilitan los botones: Añadir y Volver
            2) Si hemos seleccionado una categoría desde la Tab de Lista se nos abrirá la Tab AddEdit. En este caso
                se hacen visibles los botones: Guardar, Borrar y Volver
         */
        layoutAddCat=(LinearLayout) v.findViewById(R.id.lay_lin_dialog_add);

        layoutEditCat=(LinearLayout) v.findViewById(R.id.lay_lin_dialog_edit);

        return v;
    }

    @Override
    public void onClick(View v){
        int seleccion = v.getId();

        switch (seleccion){
            case R.id.btnAddCategoria:
                if (editCategoria.getText().toString().equals("")){
                    Toast.makeText(getContext(),R.string.CampoCategoriaVacio,Toast.LENGTH_LONG).show();
                } else {
                 /*   Intent i = new Intent(getActivity(), Inventario.class);
                    i.putExtra("categoriaAdd",editCategoria.getText().toString());
                    i.putExtra("Idcategoria",0); // El hecho de que Idcategoria=0 y NomCategoria==" " nos indicará que lo que se quiere es añadir una nueva categoria
                    i.putExtra("NomCategoria","");
                    getActivity().setResult(Activity.RESULT_OK,i); */
              /*      AddInv.productos.addCategoria(editCategoria.getText().toString());
                    getActivity().finish(); */
                    InitActivity.productos.addCategoria(editCategoria.getText().toString());
                }
                break;
            case R.id.btnVolverCategoria:
                getActivity().finish(); // Hay que consultar los datos
                break;
            case R.id.btnVolverCategoriaR:
                getActivity().finish();
                break;
            case R.id.btnGuardarDialogCategoria:
              //  Toast.makeText(getContext(),"Se ha pulsado a guardar Categoría",Toast.LENGTH_LONG).show();
                Log.d("Se ha pulsado","GUARDAR");
                if (editCategoria.getText().toString().equals("")){
                    Toast.makeText(getContext(),R.string.CampoCategoriaVacio,Toast.LENGTH_LONG).show();
                } else {
                    String nomCategoria=editCategoria.getText().toString();
                    Log.d("Editor",nomCategoria);
                  //  Toast.makeText(getContext(),"Se ha pulsado a guardar Categoría:"+nomCategoria,Toast.LENGTH_LONG).show();
                   // categorias.set(idIntCategoria, nomCategoria);

                    /* FUNCIONA BIEN ===========================
                    Intent ii=new Intent(getActivity(),Inventario.class);
                    ii.putExtra("categoriaAdd",""); // Esto nos indica que lo que se quiere hacer es una modificación del nombre de una categoría
                    ii.putExtra("Idcategoria",idIntCategoria);
                    ii.putExtra("NomCategoria",nomCategoria);
                    Log.d("No ha llegado","Seguir probando");
                    getActivity().setResult(Activity.RESULT_OK,ii); */
                    // DialogCategoria_TabLista.adaptadorCategoria
                    InitActivity.productos.actualizarCategoria(idIntCategoria,nomCategoria);
                    InitActivity.productos.sacarCategorias();
                    getActivity().finish();
                }
                break;
            case R.id.btnBorrarCategoria:
             /*   Intent iii=new Intent(getActivity(),Inventario.class);
               iii.putExtra("categoriaAdd",""); // Esto nos indica que lo que se quiere hacer es una modificación del nombre de una categoría
                iii.putExtra("Idcategoria",idIntCategoria);
                iii.putExtra("NomCategoria","");
                getActivity().setResult(Activity.RESULT_OK,iii); */
                boolean deleteCat=InitActivity.productos.borrarCategoria(categoriaMod);
                if (deleteCat){
                    getActivity().finish();
                } else { // En el caso de que no se pueda borrar salta un mensaje de Warning

                    Log.d("Error","estoy dentro de warning");
                    llamarDialog(categoriaMod);
                }

            //    getActivity().finish();
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        View v=getView();
        Bundle extras=getActivity().getIntent().getExtras();
        if(extras != null) {
            Long idCategoria = extras.getLong("id_categoria", -1);
            if (idCategoria != -1) {
                idIntCategoria=idCategoria.intValue();
                categoriaMod=categorias.get(idIntCategoria);
                Log.d("Elemento Seleccionado",categorias.get(idIntCategoria));
                editCategoria.setText(categorias.get(idCategoria.intValue())); //categorias[idCategoria.intValue()]
            }
            Boolean flagVisivility=extras.getBoolean("visivility",false);
            if(flagVisivility==true){
                layoutAddCat.setVisibility(View.INVISIBLE);
                layoutEditCat.setVisibility(View.VISIBLE);
            }
        }
    }

    // Dialog de warning en caso de que existan productos con la categoría a eliminar
    public void llamarDialog(String categoria){
        dialogWarning = new Dialog(getActivity(), R.style.Theme_Dialog_Translucent);

        // Deshabilitamos el titulo por defecto
        dialogWarning.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // obligamos al usuario a tener que pulsar los botones
        dialogWarning.setCancelable(false);

        dialogWarning.setContentView(R.layout.dialog_warning);

        TextView titulo = (TextView) dialogWarning.findViewById(R.id.titulo);
        titulo.setText(R.string.dialog_tit);

        TextView contenido = (TextView) dialogWarning.findViewById(R.id.contenido);
        contenido.setText(getResources().getString(R.string.dialog_cont_1)
                            +categoria+getResources().getString(R.string.dialog_cont_2));

        ((Button) dialogWarning.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Comprobación del radioButton y detectar ruta seleccionada
                dialogWarning.dismiss();
            }
        });

        dialogWarning.show();
    }

}
