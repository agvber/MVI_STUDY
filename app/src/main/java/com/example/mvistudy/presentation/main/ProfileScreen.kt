package com.example.mvistudy.presentation.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvistudy.model.ProfileSidEffect
import com.example.mvistudy.model.ProfileState
import com.example.mvistudy.ui.theme.MVI_STUDYTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ProfileRoute(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val state: ProfileState by profileViewModel.collectAsState()
    val sideEffect: ProfileSidEffect by profileViewModel.container.sideEffectFlow.collectAsState(
        initial = ProfileSidEffect.Init
    )

    val context = LocalContext.current

    if (sideEffect is ProfileSidEffect.ServerError) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    ProfileScreen(
        onChangeUsername = profileViewModel::changeUsername,
        onChangeGender = {},
        onChangeIntroduce = profileViewModel::changeIntroduce,
        profileState = state
    )
}

@Composable
fun ProfileScreen(
    onChangeUsername: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onChangeIntroduce: (String) -> Unit,
    profileState: ProfileState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 48.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Column {
            Text(text = "Username")
            TextField(
                value = profileState.username,
                onValueChange = onChangeUsername
            )
        }

        Column {
            Text(text = "Gender")
            TextField(
                value = profileState.gender.name,
                onValueChange = onChangeGender
            )
        }

        Column {
            Text(text = "Introduce")
            TextField(
                value = profileState.introduce,
                onValueChange = onChangeIntroduce
            )
        }

        Column {
            Text(text = "Username")
            TextField(
                value = profileState.username,
                onValueChange = onChangeUsername
            )
        }

    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    MVI_STUDYTheme {
        ProfileScreen(
            onChangeUsername = {},
            onChangeGender = {},
            onChangeIntroduce = {},
            profileState = ProfileState()
        )
    }
}