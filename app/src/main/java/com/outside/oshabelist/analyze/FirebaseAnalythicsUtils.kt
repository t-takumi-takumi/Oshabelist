package com.outside.oshabelist.analyze

import android.os.Bundle
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object FirebaseAnalyticsUtils {
    fun sendEvent(name: FirebaseEvent.Name, bundle: Bundle? = null) {
        Firebase.analytics.logEvent(name.value, bundle)
    }
}