# Flashy

A Java with Maven program that combines the flashcard study technique with a pomodoro timer.


## Flashcards

Create, save, and import flashcards. Flashcards are sorted by topic, and topics are sorted by categories. Flashcards are saved in a file system in the root of the project usng JSON. Begin by selecting a topic to study, then shift through all the flashcards in the topic. If the flashcard is easy, you can click a button that will temporarily take it out of the pool.

## Pomodoro Timer

Set between intervals of 25 or 50 minutes and use it alongside flashcards as a seperate window.

## Dependencies

### json

Used for the saving flashcards functionality.

### jackson-databind

Assists in reading/writing the flashcards.

### gson

Converts flashcard objects to JSON and vice versa.

### jfreechart

Used to create graphs on home page.
