package sdu.sitta.ungrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by sitta on 27/4/2560.
 */

public class MyAdapter extends BaseAdapter{
    private Context objcontext;
    private String[] foodString,sourceString,priceString;

    public MyAdapter(Context context, String[] foodString, String[] sourceString, String[] priceString) {
        this.objcontext = context;
        this.foodString = foodString; // ชื่อ
        this.sourceString = sourceString; // รูป

        this.priceString = priceString; // ราคา
    }

    @Override
    public int getCount() {
       return foodString.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = objLayoutInflater.inflate(R.layout.my_listview, parent,false);

        // show food
        TextView foodTextView = (TextView) view1.findViewById(R.id.txtTitleLiv);

        //show price
        TextView priceTextView = (TextView) view1.findViewById(R.id.txtDetailLiv);

        //show imagefood
        ImageView foodImageView = (ImageView) view1.findViewById(R.id.imvIcon);

        Picasso.with(objcontext).load(sourceString[position]).into(foodImageView);
        foodTextView.setText(foodString[position]);
        priceTextView.setText(priceString[position]);

        return view1;
    }
}
