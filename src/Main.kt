enum class VehicleType { CAR, TRUCK, MOTORCYCLE }

interface Drivable {
    fun drive()
}

// Custom Exception
class InvalidOwnerException(message: String) : Exception(message)

class Vehicle<T>(val type: VehicleType, private val owner: T) : Drivable {
    init {
        if (owner is String && owner.isBlank()) {
            throw InvalidOwnerException("Owner name cannot be blank!")
        }
    }

    override fun drive() {
        println("$owner is driving a $type.")
    }
}

fun main() {
    try {
        val car = Vehicle(VehicleType.CAR, "Alice")
        val truck = Vehicle(VehicleType.TRUCK, "Bob")
        val invalidVehicle = Vehicle(VehicleType.MOTORCYCLE, "") // This will throw an exception

        car.drive()
        truck.drive()
        invalidVehicle.drive()  // This line won't execute due to the exception above
    } catch (e: InvalidOwnerException) {
        println("Error: ${e.message}")
    } catch (e: Exception) {
        println("Unexpected error: ${e.message}")
    } finally {
        println("Program execution finished.")
    }
}
