package as.founder.demo.wang.wyyyyPlayView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import as.founder.demo.wang.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WyyyyViewActivity extends AppCompatActivity {

    @BindView(R.id.iv_play)
    ImageView playPause;
    @BindView(R.id.musicplay_view)
    MusicPlayerView musicplayView;
    @OnClick(R.id.iv_play)
    public void musicClick(View view){
        if(musicplayView.isPlaying()){
            musicplayView.pauseMusic();
            playPause.setSelected(false);
        }else{
            musicplayView.playMusic();
            playPause.setSelected(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyyyy_view);
        ButterKnife.bind(this);
        musicplayView.playMusic();
        playPause.setSelected(true);
    }
}
