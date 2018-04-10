package ztml.dev.ngokhacbac.exampelasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButtonStart;
    private Button mButtonEnd;
    private ProgressBar mProgressBar;
    private TextView mTextShowPercent;
    private CoutNumber mMyTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonEnd = findViewById(R.id.buttonEnd);
        mButtonStart = findViewById(R.id.buttonStart);
        mProgressBar = findViewById(R.id.progressBar);
        mTextShowPercent = findViewById(R.id.percentShow);
        mProgressBar.setMax(100);
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyTask = new CoutNumber();
                mMyTask.execute(100);
            }
        });
        mButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyTask.cancel(true);

            }
        });
    }

    public class CoutNumber extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            for (int i = 0; i < integers[0]; i++) {
                try {
                    if (isCancelled() == true) {
                        break;
                    }
                    publishProgress(i);
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mTextShowPercent.setText(values[0] + "%");
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Finish", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
        }
    }
}
