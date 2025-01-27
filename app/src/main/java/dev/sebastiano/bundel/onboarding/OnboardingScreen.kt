package dev.sebastiano.bundel.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.sebastiano.bundel.BundelTheme
import dev.sebastiano.bundel.R

@Preview(name = "Onboarding screen (needs permission)", showSystemUi = true)
@Composable
internal fun OnboardingScreenNeedsPermissionPreview() {
    BundelTheme {
        OnboardingScreen(
            true,
            onSettingsIntentClick = { },
            onDismissClicked = { },
            false,
            {}
        )
    }
}

@Preview(name = "Onboarding screen (dismiss only)", showSystemUi = true)
@Composable
internal fun OnboardingScreenDismissOnlyPreview() {
    BundelTheme {
        OnboardingScreen(
            false,
            onSettingsIntentClick = { },
            onDismissClicked = { },
            true,
            {}
        )
    }
}

@Composable
internal fun OnboardingScreen(
    needsPermission: Boolean,
    onSettingsIntentClick: () -> Unit,
    onDismissClicked: () -> Unit,
    crashReportingEnabled: Boolean,
    onSwitchChanged: (Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.h2)

        Spacer(modifier = Modifier.height(32.dp))

        if (needsPermission) {
            RequestNotificationsAccess(onSettingsIntentClick)
        } else {
            Button(onClick = onDismissClicked) {
                Text(text = "Let's a-go!")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        CrashlyticsSwitch(crashReportingEnabled, onSwitchChanged)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CrashlyticsSwitch(
    crashReportingEnabled: Boolean,
    onSwitchChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                "Enable Crash reporting",
                style = MaterialTheme.typography.h5
            )
        }
        Column {
            Switch(
                checked = crashReportingEnabled,
                onCheckedChange = { onSwitchChanged(!crashReportingEnabled) },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.End),
            )
        }
    }
}

@Composable
private fun RequestNotificationsAccess(onSettingsIntentClick: () -> Unit) {
    Text(
        text = stringResource(R.string.notifications_permission_explanation),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(bottom = 16.dp)
    )
    Button(
        onClick = onSettingsIntentClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(stringResource(R.string.button_notifications_access_prompt))
    }
}
