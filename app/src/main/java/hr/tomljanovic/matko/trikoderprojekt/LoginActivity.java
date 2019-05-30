package hr.tomljanovic.matko.trikoderprojekt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
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

    public static boolean isValidEmail(CharSequence target) {  // Email validator, checks if field has correct input
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @OnClick(R.id.btnLogin)
    public void loginButton() {

        if (etEMail.getText().length() == 0 || etPassword.getText().length() == 0) {  // Is any field empty?
            Toast.makeText(this, getString(R.string.enter_info), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(etEMail.getText().toString().trim())) {   // Is the Email valid?
            Toast.makeText(this, getString(R.string.invalid_mail), Toast.LENGTH_SHORT).show();
            return;
        }
        finish();  // If all info is correct return to the previous screen and welcome user
        Toast.makeText(this, getString(R.string.welcome) + " " + etEMail.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.imageButton)  // Return to the previous screen
    public void goBack() {
        finish();
    }
}
