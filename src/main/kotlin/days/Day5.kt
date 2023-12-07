package days

import java.util.*
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrDefault
import kotlin.math.max
import kotlin.math.min
import kotlin.streams.asStream

fun <T> List<T>.tail() = drop(1)

data class Range(val destination: LongRange, val source: LongRange)

fun Range.findDestination(source: Long) = if (this.source.contains(source)) {
    val max = max(this.source.first, source)
    val min = min(this.source.first, source)
    val index = max - min
    Optional.of(this.destination.first + index)
} else {
    Optional.empty()
}

fun List<Range>.findDestination(source: Long) =
    this.map { it.findDestination(source) }.filter { it.isPresent }.map { it.get() }.firstOrNull() ?: source

fun Range.zip() = destination zip source

fun Range.getDestination(source: Long): Optional<Pair<Long, Long>> {
    println("Range.getDestination($source)")

    return Optional.ofNullable(this.zip().filter { (dest, src) -> src == source }.firstOrNull())
}

fun List<Range>.getDestination(source: Long): Long {
    println("List<Range>.getDestination($source)")
    return this.map { it.getDestination(source) }.filter { it.isPresent }.map { it.get().first }.firstOrNull() ?: source
}

data class Data(
    val seeds: List<Long> = emptyList(),
    val seedToSoil: List<Range> = emptyList(),
    val soilToFertilizer: List<Range> = emptyList(),
    val fertilizerToWater: List<Range> = emptyList(),
    val waterToLight: List<Range> = emptyList(),
    val lightToTemperature: List<Range> = emptyList(),
    val temperatureToHumidity: List<Range> = emptyList(),
    val humidityToLocation: List<Range> = emptyList(),
)

fun ParseDay5(input: String): Data {
    val parts = input.split("\n\n")
    val seeds = parts[0].split(":")[1].trim().split(" ").map { it.toLong() }
    val seedToSoil = parts[1].split("\n").tail().map { it.split(" ").map { it.toLong() } }
        .map { Range(LongRange(it[0], it[0] + (it[2] - 1)), LongRange(it[1], it[1] + (it[2] - 1))) }
    val soilToFertilizer = parts[2].split("\n").tail().map { it.split(" ").map { it.toLong() } }
        .map { Range(LongRange(it[0], it[0] + (it[2] - 1)), LongRange(it[1], it[1] + (it[2] - 1))) }
    val fertilizerToWater = parts[3].split("\n").tail().map { it.split(" ").map { it.toLong() } }
        .map { Range(LongRange(it[0], it[0] + (it[2] - 1)), LongRange(it[1], it[1] + (it[2] - 1))) }
    val waterToLight = parts[4].split("\n").tail().map { it.split(" ").map { it.toLong() } }
        .map { Range(LongRange(it[0], it[0] + (it[2] - 1)), LongRange(it[1], it[1] + (it[2] - 1))) }
    val lightToTemperature = parts[5].split("\n").tail().map { it.split(" ").map { it.toLong() } }
        .map { Range(LongRange(it[0], it[0] + (it[2] - 1)), LongRange(it[1], it[1] + (it[2] - 1))) }
    val temperatureToHumidity = parts[6].split("\n").tail().map { it.split(" ").map { it.toLong() } }
        .map { Range(LongRange(it[0], it[0] + (it[2] - 1)), LongRange(it[1], it[1] + (it[2] - 1))) }
    val humidityToLocation = parts[7].split("\n").tail().map { it.split(" ").map { it.toLong() } }
        .map { Range(LongRange(it[0], it[0] + (it[2] - 1)), LongRange(it[1], it[1] + (it[2] - 1))) }

    return Data(
        seeds,
        seedToSoil,
        soilToFertilizer,
        fertilizerToWater,
        waterToLight,
        lightToTemperature,
        temperatureToHumidity,
        humidityToLocation
    )
}


data class Result(
    val seed: Long = 0,
    val soil: Long = 0,
    val fertilizer: Long = 0,
    val water: Long = 0,
    val light: Long = 0,
    val temperature: Long = 0,
    val humidity: Long = 0,
    val location: Long = 0
)

fun Data.part1() = this.seeds.map { seedId ->
    val soil = this.seedToSoil.findDestination(seedId)
    val fertilizer = this.soilToFertilizer.findDestination(soil)
    val water = this.fertilizerToWater.findDestination(fertilizer)
    val light = this.waterToLight.findDestination(water)
    val temperature = this.lightToTemperature.findDestination(light)
    val humidity = this.temperatureToHumidity.findDestination(temperature)
    val location = this.humidityToLocation.findDestination(humidity)
    Result(
        seed = seedId,
        soil = soil,
        fertilizer = fertilizer,
        water = water,
        light = light,
        temperature = temperature,
        humidity = humidity,
        location = location
    )
}.map { it.location }.min()

fun Data.part2() =
    this.seeds.chunked(2).parallelStream().map { (start, end) ->
        (start..start + end).asSequence().asStream().map {
            val soil = this.seedToSoil.findDestination(it)
            val fertilizer = this.soilToFertilizer.findDestination(soil)
            val water = this.fertilizerToWater.findDestination(fertilizer)
            val light = this.waterToLight.findDestination(water)
            val temperature = this.lightToTemperature.findDestination(light)
            val humidity = this.temperatureToHumidity.findDestination(temperature)
            val location = this.humidityToLocation.findDestination(humidity)
            location
        }.min { o1, o2 -> o1.compareTo(o2) }.getOrDefault(Long.MAX_VALUE)
    }.min { o1, o2 -> o1.compareTo(o2) }.getOrDefault(Long.MAX_VALUE)