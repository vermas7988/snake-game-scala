# snake-game-scala

This repository contains basic implementation of snake game using plain scala.

Summary: *Snake game*

Controllers: `w,a,s,d`

Run: `sbt run`

Tech used: plain scala with sbt


- You control a Snake that moves around in a grid, the snake spans over a couple cells at the beginning.
- You can only turn the snake left or right and it moves one cell every "game turn".
- There is always one food pellet on the grid.
- Goal of the game is to eat said pellets by moving the snake to its position.
- When the snake eats a pellet it grows one cell longer.
- If the snake collides with itself, the game is lost.
- If the snake reaches one of the grid's borders, it wraps around (i.e. going out on the left side means you come back in on the right side).
