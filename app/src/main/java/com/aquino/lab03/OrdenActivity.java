package com.aquino.lab03;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;



public class OrdenActivity extends AppCompatActivity {

    private Spinner spinner1;
    private RadioGroup radioGroup;
    private CheckBox checkBox1,checkBox2;
    String[] pizzas={"Americana", "Meet Lover", "Suprema", "Super Suprema"};

    int costo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);

        radioGroup =  (RadioGroup) findViewById(R.id.radioGroup);

        spinner1 = (Spinner)findViewById(R.id.spinner);


    }

    //Metodo onclick
    public void mostrarResultado(View v) {

        String item = (String) spinner1.getSelectedItem();

        if (item.equals(pizzas[0])) {
            costo = 40;
        } else if (item.equals(pizzas[1])) {
            costo = 60;
        } else if (item.equals(pizzas[2])) {
            costo = 45;
        } else if (item.equals(pizzas[3])) {
            costo = 65;
        }

        // Radio Button
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radioButtonId);
        CharSequence nom_masa= radioButton.getText();


        // Checkbox
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        if (checkBox1.isChecked() && checkBox2.isChecked()) {
            costo = costo + 20;
        } else if (checkBox2.isChecked()) {
            costo = costo + 12;
        } else if (checkBox1.isChecked()) {
            costo = costo + 8;
        }


        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Confirmación de pedido");
        alertDialog.setMessage("Su pedido de "+item+" con "+nom_masa+" a S/."+costo+".00 + IGV está en proceso de envío.");
        // Alert dialog button
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Alert dialog action goes here
                        dialog.dismiss();// use dismiss to cancel alert dialog
                    }
                });
        alertDialog.show();




        Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


        // Notification
        final Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Confirmación pedido de pizza")
                .setContentText("¡Su orden ya está en camino!")
                .setSmallIcon(R.drawable.ic_pizza)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();


        final   NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                notificationManager.notify(0, notification);
            }
        }, 10000);


    }

}