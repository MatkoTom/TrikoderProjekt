package hr.tomljanovic.matko.trikoderprojekt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import hr.tomljanovic.matko.trikoderprojekt.R;

public class SpendingsAdapter extends ArrayAdapter<Spendings> {  //Create custom adapter to use with the list

    @BindView(R.id.tvSpender)
    TextView tvSpender;

    @BindView(R.id.tvAmount)
    TextView tvAmount;

    public SpendingsAdapter(Context context, ArrayList<Spendings> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Spendings user = getItem(position);
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        tvSpender = convertView.findViewById(R.id.tvSpender);
        tvAmount = convertView.findViewById(R.id.tvAmount);

        tvSpender.setText(user.spender);
        tvAmount.setText(user.amount);
        return convertView;
    }
}
