package com.tecnoappsmobile.managershop_v5.ActivityInventario;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tecnoappsmobile.managershop_v5.DivideItemDecoration;
import com.tecnoappsmobile.managershop_v5.InitActivity;
import com.tecnoappsmobile.managershop_v5.R;

// import com.tecnoappsmobile.managershop_v5.MainActivity;

/**
 * Created by DANIEL on 29/11/2016.
 */

public class BuscarProducto extends Dialog implements View.OnClickListener {
    final static int SCANNER=3;
    private RecyclerView recylerBuscar;
    public static AdaptadorBuscar adaptadorBuscar;
    private RecyclerView.LayoutManager layoutManBuscar;
    private Context contexto;

    private ImageButton btnCodigo,btnBuscar;

    private EditText editBuscar;

    private int flag_buscar=0;

    // Cuadros de diálogo
    private Dialog errorBusqueda;
    DialogBuscaResult mResult;


    public BuscarProducto(Context contexto){
        super(contexto);
        this.contexto=contexto;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_buscar_producto);

        // Creamos el recycler view
        recylerBuscar=(RecyclerView) findViewById(R.id.recyBuscar);
        adaptadorBuscar=new AdaptadorBuscar(contexto, InitActivity.productos,
                InitActivity.productos.extraeCursorNomCod("")); // Le pasamos la base de datos para
        recylerBuscar.setAdapter(adaptadorBuscar);
        layoutManBuscar=new LinearLayoutManager(contexto);
        recylerBuscar.setLayoutManager(layoutManBuscar);
        recylerBuscar.addItemDecoration(new DivideItemDecoration(contexto));

        adaptadorBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i=new Intent(getContext(),Inventario.class);
                i.putExtra("id",(long) recylerBuscar.getChildAdapterPosition(v));
                Log.d("se selecionado",String.valueOf((long) recylerBuscar.getChildAdapterPosition(v))); */
                Log.d("se selecionado",String.valueOf((long) recylerBuscar.getChildAdapterPosition(v)));
                mResult.finishDialog((long) recylerBuscar.getChildAdapterPosition(v));
                BuscarProducto.this.dismiss();
            }
        });

        // Edit de búsqueda
        editBuscar=(EditText) findViewById(R.id.buscar_edit);

        // Botones
        btnCodigo=(ImageButton) findViewById(R.id.btn_buscar_cod);
        btnCodigo.setOnClickListener(this);
        btnBuscar=(ImageButton) findViewById(R.id.btn_buscar_producto);
        btnBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int sel = v.getId();
        String buscar;

        if (sel == R.id.btn_buscar_cod) { // Llamar la barcode
            /*Intent intent2= new Intent(getContext(), CodeScanner.class);
            //startActivity(intent1);
            startActivityForResult(intent2, SCANNER); */
            flag_buscar=1;

        } else if (sel == R.id.btn_buscar_producto) {
            buscar = editBuscar.getText().toString();
            if (buscar.isEmpty()) {
                // Error no se puede buscar con el campo vacío, mostrar cuadro
                llamarDialogBus(contexto);
            } else {
                adaptadorBuscar.setCursor(InitActivity.productos.extraeCursorNomCod(buscar));
                adaptadorBuscar.notifyDataSetChanged();
            }

        }

    }

    public void setDialogResult(DialogBuscaResult dialogResult){
        mResult=dialogResult;
    }

    public interface DialogBuscaResult {
        void finishDialog(long result);
    }

    // AVISO: No se puede dejar el campo vacio.
    public void llamarDialogBus(Context contexto){
        errorBusqueda = new Dialog(contexto, R.style.Theme_Dialog_Translucent);

        // Deshabilitamos el titulo por defecto
        errorBusqueda.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // obligamos al usuario a tener que pulsar los botones
        errorBusqueda.setCancelable(false);

        errorBusqueda.setContentView(R.layout.dialog_warning);

        TextView titulo = (TextView) errorBusqueda.findViewById(R.id.titulo);
        titulo.setText(R.string.titl_dialogBuscar_error);

        TextView contenido = (TextView) errorBusqueda.findViewById(R.id.contenido);
        contenido.setText(R.string.content_dialogBuscar_error);

        ((Button) errorBusqueda.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorBusqueda.dismiss();
            }
        });

        errorBusqueda.show();

    }

   /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCANNER && resultCode == Activity.RESULT_OK) { //&& uriFoto!=null){
            String codigo = data.getDataString();
            editBuscar.setText(codigo);
            Toast.makeText(getContext(), codigo, Toast.LENGTH_LONG).show();
        }
    }*/


   public int getFlag_buscar(){
       return flag_buscar;
   }
}
