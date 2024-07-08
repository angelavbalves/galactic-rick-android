package com.angelavbalves.galactic_rick_android.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.angelavbalves.galactic_rick_android.commons.utils.RequestResult
import com.angelavbalves.galactic_rick_android.ui.models.CharacterDetailsPresentation
import com.angelavbalves.galactic_rick_android.ui.viewmodels.CharacterDetailsViewModel

@Composable
fun CharacterDetails(
    id: Int,
    viewModel: CharacterDetailsViewModel,
    navController: NavController
) {
    val charactersState by remember { viewModel.fetchCharacter(id = id) }.observeAsState(initial = RequestResult.Loading)

    LaunchedEffect(Unit) {
        viewModel.fetchCharacter(id)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val result = charactersState) {
            is RequestResult.Loading -> {
                com.angelavbalves.galactic_rick_android.ui.home.LoadingScreen()
            }
            is RequestResult.Success -> {
                CharacterDetailsScreen(character = result.data) {
                    navController.navigateUp()
                }
            }
            is RequestResult.Error -> {
                com.angelavbalves.galactic_rick_android.ui.home.ErrorScreen(error = (charactersState as RequestResult.Error).exception.message)
            }
        }
    }
}


@Composable
fun CharacterDetailsScreen(
    character: CharacterDetailsPresentation,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Informações do personagem") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                backgroundColor = Color.Transparent,
                contentColor = Color.White,
                elevation = 0.dp
            )
        },
        backgroundColor = Color(0xFF2E7D32)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = character.characterName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Text(
                text = character.characterName,
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            InfoCard(character = character)
        }

    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun InfoCard(character: CharacterDetailsPresentation) {
    Card(
        backgroundColor = Color(0xFF95B697),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Info",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp),
            )

            InfoRow(label = "Status", value = character.status.status)
            InfoRow(label = "Espécie", value = character.specie)
            InfoRow(label = "Gênero", value = character.gender)
            InfoRow(label = "Localização", value = character.locationName)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(error: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error ?: "An error occurred",
            color = MaterialTheme.colors.error,
            modifier = Modifier.padding(16.dp)
        )
    }
}