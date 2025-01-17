import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.travail_app.R

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { Navbar() },
        bottomBar = { BottomBar() },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()) // Défilement vertical activé
            ) {
                Spacer(modifier = Modifier.height(16.dp)) // Espace sous la Navbar

                // Barre de recherche
                SearchBar()

                Spacer(modifier = Modifier.height(16.dp))

                // Section des catégories
                HorizontalScrollableSection()

                Spacer(modifier = Modifier.height(16.dp))

                // Section de réservation des vols
                BookFlightSection()

                Spacer(modifier = Modifier.height(16.dp))

                // Section des meilleures offres
                BestOffersSection()

                Spacer(modifier = Modifier.height(16.dp))

                // Section des voyages d'hiver
                WinterJourneySection()

                Spacer(modifier = Modifier.height(16.dp))

                // Section des lieux populaires
                PopularLocationsSection()

                Spacer(modifier = Modifier.height(16.dp))

                // Section des réalisations
                MyAchievementsSection()
            }
        }
    )
}



@Composable
fun Navbar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = Color(0xFF6C63FF),
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Contenu optionnel ici
            }

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.LightGray, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        MorningMessage()

    }
}

@Composable
fun MorningMessage() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Good Morning, Shreya....",
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Make plan for weekend",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Barre de recherche
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFFF6F6F6),shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            var searchText by remember { mutableStateOf("") }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24), // Icône loupe
                    contentDescription = "Search",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                    modifier = Modifier.weight(1f),
                    decorationBox = { innerTextField ->
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Search Places",
                                style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = Color(0xFF6C63FF), shape = RoundedCornerShape(5.dp))
                .clickable { /* Action sur clic du bouton */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icone),
                contentDescription = "Filter",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
@Composable
fun HorizontalScrollableSection() {
    val items = listOf(
        Pair("Places", R.drawable.red),
        Pair("Flights", R.drawable.plane),
        Pair("Trains", R.drawable.train),
        Pair("Buses", R.drawable.busstop),
        Pair("Taxi", R.drawable.taxi)
    )
    var selectedCategory by remember { mutableStateOf("Flights") } // Tracks selected category

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { item ->
            CategoryItem(
                name = item.first,
                iconRes = item.second,
                isSelected = item.first == selectedCategory,
                onClick = { selectedCategory = item.first } // Updates selected category on click
            )
        }
    }
}

@Composable
fun CategoryItem(name: String, iconRes: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(80.dp) // Largeur fixe pour uniformité
            .height(100.dp)
            .clickable { onClick() } // Gestion du clic
    ) {
        // Cercle extérieur avec une bordure conditionnelle
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(68.dp) // Taille extérieure pour inclure la bordure
                .clip(CircleShape) // Assure la forme circulaire
                .background(Color.Transparent) // Fond transparent
                .border(
                    width = 2.dp, // Épaisseur de la bordure
                    color = if (isSelected) Color(0xFF6C63FF) else Color.Transparent,
                    shape = CircleShape
                )
        ) {
            // Cercle intérieur avec fond
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp) // Taille de l'intérieur
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = name,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            color = if (isSelected) Color.Black else Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}


@Composable
fun BookFlightSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Book your Flight",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Flight Type Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FlightTypeButton(text = "One Way", isSelected = true)
            FlightTypeButton(text = "Round Trip", isSelected = false)
            FlightTypeButton(text = "Multicity", isSelected = false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // From and To Fields with Swap Button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // From Field
            FlightTextField(placeholder = "Choose Departure from")

            Spacer(modifier = Modifier.height(8.dp))

            // Swap Button
            IconButton(
                onClick = { /* Swap logic */ },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF6C63FF), shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_autorenew_24),
                    contentDescription = "Swap",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // To Field
            FlightTextField(placeholder = "Choose Arrival at")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Departure Date Field
        FlightTextField(
            placeholder = "Choose your Date",
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                    contentDescription = "Calendar",
                    tint = Color(0xFF6C63FF),
                    modifier = Modifier.size(24.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Passengers Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PassengerCounter(label = "Adult (12+)", initialCount = 0)
            PassengerCounter(label = "Childs (2-12)", initialCount = 0)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Button
        Button(
            onClick = { /* Action pour rechercher un vol */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C63FF)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Search Flight",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FlightTypeButton(text: String, isSelected: Boolean) {
    Text(
        text = text,
        color = if (isSelected) Color.White else Color.Gray,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFF6C63FF) else Color(0xFFF6F6F6),
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { /* Gérer la sélection */ }
    )
}

@Composable
fun FlightTextField(placeholder: String, trailingIcon: @Composable (() -> Unit)? = null) {
    var textValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6), shape = MaterialTheme.shapes.small)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BasicTextField(
                value = textValue,
                onValueChange = { textValue = it },
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (textValue.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            )
            trailingIcon?.invoke()
        }
    }
}

@Composable
fun PassengerCounter(label: String, initialCount: Int) {
    var count by remember { mutableStateOf(initialCount) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { if (count > 0) count-- }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_remove_24),
                    contentDescription = "Decrease",
                    tint = Color(0xFF6C63FF)
                )
            }
            Text(
                text = count.toString(),
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            )
            IconButton(onClick = { count++ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_box_24),
                    contentDescription = "Increase",
                    tint = Color(0xFF6C63FF)
                )
            }
        }
    }
}
@Composable
fun TravelDashboard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Section: Best Offers
        SectionHeader(title = "Best offers", onSeeAllClick = {})
        BestOffersSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Section: Winter Journey
        SectionHeader(title = "Winter Journey", onSeeAllClick = {})
        WinterJourneySection()

        Spacer(modifier = Modifier.height(16.dp))

        // Section: Popular Location
        SectionHeader(title = "Popular Location", onSeeAllClick = {})
        PopularLocationsSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Section: My Achievements
        MyAchievementsSection()
    }
}

@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Text(
            text = "See all",
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            color = Color(0xFF6C63FF),
            modifier = Modifier.clickable { onSeeAllClick() }
        )
    }
}

@Composable
fun BestOffersSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OfferCard(
            imageRes = R.drawable.image0,
            title = "Couple's Gift for Couples",
            discount = "Up to 30% OFF",
            buttonText = "View Detail"
        )
        OfferCard(
            imageRes = R.drawable.image1,
            title = "For Winter Holidays",
            discount = "Up to 20% OFF",
            buttonText = "View Detail"
        )
    }
}

@Composable
fun OfferCard(imageRes: Int, title: String, discount: String, buttonText: String) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Text(
            text = discount,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buttonText,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
            color = Color(0xFF6C63FF),
            modifier = Modifier.clickable { /* Handle click */ }
        )
    }
}

@Composable
fun WinterJourneySection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        JourneyCard(imageRes = R.drawable.image2, title = "Shimla Best Kept Secret")
        JourneyCard(imageRes = R.drawable.image3, title = "Charming Kasol Vibes")
    }
}

@Composable
fun JourneyCard(imageRes: Int, title: String) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            color = Color.Black
        )
    }
}

@Composable
fun PopularLocationsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LocationCard(
            imageRes = R.drawable.image4,
            name = "Eiffel Tower",
            location = "Paris Eytel Kulesi",
            distance = "2450 KMS"
        )
        LocationCard(
            imageRes = R.drawable.image5,
            name = "Beautiful China",
            location = "Shanghai, China",
            distance = "6000 KMS"
        )
    }
}

@Composable
fun LocationCard(imageRes: Int, name: String, location: String, distance: String) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            color = Color.Black
        )
        Text(
            text = location,
            style = TextStyle(fontSize = 12.sp, color = Color.Gray)
        )
        Text(
            text = distance,
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
            color = Color.Black
        )
    }
}

@Composable
fun MyAchievementsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF6C63FF), shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "🎁 1/10 Journeys",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            color = Color.White
        )
    }
}

@Composable
fun BottomBar() {
    BottomAppBar(
        containerColor = Color.White,
        contentPadding = PaddingValues(8.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarItem(
                    icon = R.drawable.li_home,
                    contentDescription = "Home",
                    selected = true
                )
                BottomBarItem(
                    icon = R.drawable.li_briefcase,
                    contentDescription = "Bag",
                    selected = false
                )
                BottomBarItem(
                    icon = R.drawable.li_bookmark,
                    contentDescription = "Bookmark",
                    selected = false
                )
                BottomBarItem(
                    icon = R.drawable.li_user,
                    contentDescription = "Profile",
                    selected = false
                )
            }
        }
    )
}

@Composable
fun BottomBarItem(icon: Int, contentDescription: String, selected: Boolean) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        tint = if (selected) Color(0xFF8E44AD) else Color.Gray,
        modifier = Modifier.size(24.dp)
    )
}