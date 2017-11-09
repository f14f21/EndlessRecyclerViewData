package ir.hirkanhost.EndlessRecyclerViewData;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ir.hirkanhost.librarythegreatadapter.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager mylinearLayout;
    private MyAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv);
        mylinearLayout = new LinearLayoutManager(this);
        rv.setLayoutManager(mylinearLayout);

        rv.addOnScrollListener(new MyEndlessRV(mylinearLayout));

        myadapter = new MyAdapter();
        rv.setAdapter(myadapter);

    }

    class MyEndlessRV extends EndlessRecyclerViewScrollListener{

        public MyEndlessRV(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

            rows +=20;
            myadapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Page : " + page,Toast.LENGTH_SHORT).show();
        }
    }


    int rows = 20;

    class MyAdapter extends RecyclerView.Adapter<VH>{

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.row,parent,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return rows;
        }
    }


    class VH extends RecyclerView.ViewHolder{

        public VH(View v) {
            super(v);
        }
    }
}
