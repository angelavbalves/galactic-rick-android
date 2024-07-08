package com.angelavbalves.galactic_rick_android.ui.home
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.angelavbalves.galactic_rick_android.ui.viewmodels.CharactersViewModel
import flows.home.presentation.ui.models.CharacterHomePresentation
import flows.home.presentation.ui.models.CharacterStatusPresentation
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import com.angelavbalves.galactic_rick_android.R
import com.angelavbalves.galactic_rick_android.commons.utils.RequestResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.angelavbalves.galactic_rick_android.ui.details.ErrorScreen
import com.angelavbalves.galactic_rick_android.ui.details.LoadingScreen

@Composable
fun CharactersHome(
    navController: NavHostController,
    viewModel: CharactersViewModel
) {
    val charactersState by remember { viewModel.fetchCharacters() }.observeAsState(initial = RequestResult.Loading)

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val result = charactersState) {
            is RequestResult.Loading -> {
                LoadingScreen()
            }
            is RequestResult.Success -> {
                RickAndMortyScreen(characters = result.data, navController)
            }
            is RequestResult.Error -> {
                ErrorScreen(error = (charactersState as RequestResult.Error).exception.message)
            }
        }
    }
}

@Composable
fun RickAndMortyScreen(
    characters: List<CharacterHomePresentation>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E7D32))
            .padding(16.dp)
    ) {
        TopSection()
        SearchBar()
        StatusFilters()
        CharacterList(characters, navController)
    }
}

@Composable
fun TopSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "E aí, gênio! O que você quer descobrir sobre esses imbecis?",
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SearchBar() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color.White, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Digite o nome do personagem") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() },
            ),
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
        IconButton(onClick = {
            focusRequester.requestFocus()
            keyboardController?.show()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.fi_rr_search),
                contentDescription = "Search",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun StatusFilters() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatusFilter("Todos")
        StatusFilter("Vivo")
        StatusFilter("Morto")
        StatusFilter("Desconhecido")
    }
}

@Composable
fun StatusFilter(status: String) {
    Button(
        onClick = { /* Filtrar personagens */ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(text = status, color = Color.Black)
    }
}

@Composable
fun CharacterList(
    characters: List<CharacterHomePresentation>,
    navController: NavHostController
) {
    LazyColumn {
        items(characters) { character ->
            CharacterRow(character, navController)
        }
    }
}

@Composable
fun CharacterRow(
    character: CharacterHomePresentation,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFF95B697), shape = RoundedCornerShape(6.dp))
            .padding(8.dp)
            .clickable(onClick = {
                navController.navigate("details/${character.id}")
            })
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(6.dp))
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
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(6.dp))
        ) {
            Text(
                text = character.characterName,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = character.episodes,
                color = Color.White,
                fontSize = 14.sp
            )
            Text(
                text = character.status.status,
                color = when (character.status) {
                    CharacterStatusPresentation.Alive -> Color.Green
                    CharacterStatusPresentation.Dead -> Color.Red
                    else -> Color.Gray
                },
                fontSize = 14.sp
            )
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
