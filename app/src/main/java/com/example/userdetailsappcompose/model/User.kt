import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val results: List<User>
)

data class User(
    @SerializedName("name")
    val name: Name,
    @SerializedName("email")
    val email: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("dob")
    val dob: Dob
)

data class Name(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String
)

data class Location(
    @SerializedName("country")
    val country: String
)

data class Picture(
    @SerializedName("large")
    val large: String
)

data class Dob(
    @SerializedName("date")
    val date: String
)

