package hr.tomljanovic.matko.trikoderprojekt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etEMail)
    EditText etEMail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.imageButton)
    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void loginButton() {
        finish();
        Toast.makeText(this, "Welcome! " + etEMail.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imageButton)
    public void goBack() {
        finish();
    }
}
