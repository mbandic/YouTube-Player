package muamerbandic.example.youtubeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID ="r5iQzD2Bs5Y"
const val YOUTUBE_PLAYLIST ="PL44D5056375BB0057"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "YoutubeActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   //     setContentView(R.layout.activity_youtube)
   //     val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout //null? mybe error nekad
        setContentView(layout)

  /*      val button1 = Button(this)
        button1.layoutParams = ConstraintLayout.LayoutParams(600, 180)
        button1.text = "Button added"
        layout.addView(button1)     */

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this) //returns the string aka key



    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, wasRestored: Boolean) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this,"Initialized Youtube Player sucessfully", Toast.LENGTH_SHORT).show()


        youTubePlayer?.setPlayerStateChangeListener((playerStateChangeListener))
        youTubePlayer?.setPlaybackEventListener((playbackEventListener))


        if(!wasRestored){
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID) //cueVIdeo
        } else {
            youTubePlayer?.play()
        }

    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
                                         youTubeInitializationResult: YouTubeInitializationResult?) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show()
        } else {
            val errorMessage = "Error while initializing the YouTubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this,errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Good the video is playing", Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video is paused", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }

    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {
            
        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "CLick the ad, or dont", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video started", Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Seen it alllllllll", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }


}