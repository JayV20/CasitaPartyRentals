package com.example.casitapartyrentals.mainModule.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.casitapartyrentals.R;
import com.example.casitapartyrentals.common.pojo.Mueble;
import com.example.casitapartyrentals.mainModule.MainPresenter;
import com.example.casitapartyrentals.mainModule.MainPresenterClass;
import com.example.casitapartyrentals.mainModule.adapters.MuebleAdapter;
import com.example.casitapartyrentals.mainModule.adapters.OnItemClickListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.bottom_bar)
    BottomAppBar bottomBar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private MainPresenter mPresenter;
    private MuebleAdapter mAdapter;
    /*
    * ciclo de vida de actividad
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter= new MainPresenterClass(this);
        mPresenter.onCreate();
    }



    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
    /*
    * Accion para abrir carrito;
    * */
    @OnClick(R.id.fab)
    public void onOpenCart() {

    }
    /*
    * mView
    * */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(int resMsg) {
        Toast.makeText(this, resMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getMuebles(ArrayList<Mueble> muebles) {
        mAdapter= new MuebleAdapter(muebles,this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    /*
    * OnItemClickListener
    * */
    @Override
    public void OnItemClick(Mueble mueble) {

    }
}
