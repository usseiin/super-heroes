package com.example.superheroes

import SuperheroesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.models.DataSources
import com.example.superheroes.data.models.Hero


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                SuperHeroesApp()
            }
        }
    }
}

@Composable
fun SuperHeroesApp() {
    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) {
        HeroList(heroes = DataSources().heroes)
    }
}

@Composable
fun AppTopBar() {
    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun HeroList(heroes: List<Hero>) {
    LazyColumn {
        items(heroes.size) {
            index -> HeroItem(hero = heroes[index])
        }
    }
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(72.dp)
        ) {
            HeroDescription(name = hero.nameRes, description = hero.descriptionRes)
            Spacer(modifier = modifier.width(16.dp))
            HeroImage(image = hero.imageRes)
        }
    }
}

@Composable
fun HeroImage(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = image.toString(),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun RowScope.HeroDescription(@StringRes name: Int, @StringRes description: Int) {
    Column(
        Modifier.weight(1f)
    ) {
        Text(
            stringResource(name),
            style = MaterialTheme.typography.h3
        )
        Text(
            stringResource(description),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        SuperHeroesApp()
    }
}