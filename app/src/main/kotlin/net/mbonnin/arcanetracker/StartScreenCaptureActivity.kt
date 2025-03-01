package net.mbonnin.arcanetracker

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import net.mbonnin.arcanetracker.databinding.StartScreenCaptureActivityBinding
import net.mbonnin.arcanetracker.extension.finishAndRemoveTaskIfPossible
import net.mbonnin.arcanetracker.extension.makeFullscreen
import net.mbonnin.arcanetracker.ui.main.MainActivity

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class StartScreenCaptureActivity : AppCompatActivity() {
    private var mProjectionManager: MediaProjectionManager? = null

    private lateinit var binding: StartScreenCaptureActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (Settings.get(Settings.SCREEN_CAPTURE_RATIONALE_SHOWN, false)) {
            setContentView(View(this))
            startActivity()
        } else {
            binding = StartScreenCaptureActivityBinding.inflate(android.view.LayoutInflater.from(this))
            setContentView(binding.root)

            val clickListener = { _: View ->
                startActivity()
                Settings.set(Settings.SCREEN_CAPTURE_RATIONALE_SHOWN, true)
            }

            binding.next.setOnClickListener(clickListener)
        }
    }

    override fun onResume() {
        super.onResume()
        this.makeFullscreen()
    }

    private fun startActivity() {
        mProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(mProjectionManager!!.createScreenCaptureIntent(), MainActivity.REQUEST_CODE_MEDIAPROJECTION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MainActivity.REQUEST_CODE_MEDIAPROJECTION) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val projection = mProjectionManager!!.getMediaProjection(resultCode, data)
                ScreenCaptureHolder.mediaProjectionCreated(projection)
                finishAndRemoveTaskIfPossible()
            } else {
                ScreenCaptureHolder.mediaProjectionAborted()
                AlertDialog.Builder(this)
                        .setTitle(getString(R.string.hi_there))
                        .setMessage(getString(R.string.noScreenCapture))
                        .setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
                        .show()
                finishAndRemoveTaskIfPossible()
            }
        }
    }
}