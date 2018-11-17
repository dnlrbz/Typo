package at.ac.univie.hci.typo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import at.ac.univie.hci.typo.Controller.ActivityManagement.Activities;
import at.ac.univie.hci.typo.Controller.StatisticsController;
import at.ac.univie.hci.typo.R;

public class TransportAskingActivity extends AppCompatActivity {
    private Button tram;
    private Button bus;
    private Button car;
    private Button train;
    private Button ubahn;
    private Button no;
    private String mostMissedKey;
    private Double accuracy;
    private String time;
    private int score;
    private int keysPerMinute;
    private String playerName;
    private String context;
    private StatisticsController sController;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_asking);

        bundle = getIntent().getExtras();
        mostMissedKey = bundle.getString("mostMissedKey");
        accuracy = Double.valueOf(sController.getDecimalFormat().format(bundle.getDouble("accuracy")));
        time = bundle.getString("time");
        score = bundle.getInt("score");
        keysPerMinute = bundle.getInt("keysPerMinute");
        context = bundle.getString("context");

        initializeVaribles(savedInstanceState);
        System.out.println("ENTERED TRANSPORT");
    }



    private void initializeVaribles(Bundle savedInstanceState) {
        sController = new StatisticsController();
        tram = (Button) findViewById(R.id.buttonTram);
        bus = (Button) findViewById(R.id.buttonBus);
        ubahn = (Button) findViewById(R.id.buttonUbahn);
        car = (Button) findViewById(R.id.buttonCar);
        train = (Button) findViewById(R.id.buttonTrain);
        no = (Button) findViewById(R.id.buttonNo);


        tram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = context.replace(Activities.IN_VEHICLE_ACTIVITY, Activities.IN_TRAM);
                bundle.putString("context", context);
                Intent intent = new Intent(TransportAskingActivity.this, AfterGameActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = context.replace(Activities.IN_VEHICLE_ACTIVITY, Activities.IN_CAR);
                bundle.putString("context", context);
                Intent intent = new Intent(TransportAskingActivity.this, AfterGameActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = context.replace(Activities.IN_VEHICLE_ACTIVITY, Activities.IN_TRAIN);
                bundle.putString("context", context);
                Intent intent = new Intent(TransportAskingActivity.this, AfterGameActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        ubahn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = context.replace(Activities.IN_VEHICLE_ACTIVITY, Activities.IN_UNDERGROUND);
                bundle.putString("context", context);
                Intent intent = new Intent(TransportAskingActivity.this, AfterGameActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = context.replace(Activities.IN_VEHICLE_ACTIVITY, Activities.IN_BUS);
                bundle.putString("context", context);
                Intent intent = new Intent(TransportAskingActivity.this, AfterGameActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = context.replace(Activities.IN_VEHICLE_ACTIVITY, "");
                bundle.putString("context", context);
                Intent intent = new Intent(TransportAskingActivity.this, AfterGameActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
