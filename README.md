# JavaRTS

JavaRTS is a resource management game where players build and manage a growing settlement while optimizing resources and population. This project was created as the final assignment for the Object-Oriented Approach course at the University of Bordeaux.

The course focused on design patterns, so the primary emphasis is on the code structure and application of patterns rather than graphical quality or gameplay optimization.

You will find a complete report containing the code structure, the design patterns used and why, and finally a quick explanation on the game commands in the folder resources (in French).

## Prerequisites if you want to compile the source code

- Java 21
- Apache Maven if you want to compile with maven
- JavaFX

## How to play

To launch the game, go to the folder JavaRTS and type `mvn javafx:run` or use our release.

At the beginning of the game, the player has resources to construct a few buildings and food to last for several days. These resources are displayed at the top of the screen.

At the bottom of the screen, there are two buttons corresponding to two tabs:

- Buildings Tab:
  - Displays buildings you can construct, along with information about their inhabitants, workers, daily resource production, and consumption.
  - Hovering over a building card shows its construction cost. Click on a building to select it, then place it on the map.
  - Transparent cards represent buildings you currently lack resources to construct.

- People Management Tab:
  - Shows the buildings you've placed and their statuses.
  - Displays total population and number of workers.
  - Allows managing buildings by assigning residents, removing workers, etc.
  - Errors will appear at the top of the screen (e.g., trying to assign workers to unsuitable buildings).

The game Map is in the center of the screen. Depending on your window size, the map may have gaps between tiles. To place a building, select one from the **Buildings Tab**, then click on a tile. If resources are insufficient, an error message appears. You can also click on a building on the map to open a detailed management window, where you can check its status, modify workers or residents, and delete it if needed.

### Important Tips

- Ensure enough food for your population (1 unit per person daily) to prevent deaths. This can lead to empty or unstaffed buildings, causing resource shortages.
- A building's displayed production/consumption is achieved only when fully staffed. Partial staffing results in proportional output.
- Boosting a building doubles production for 5 days at normal consumption cost but uses 1 tool and increases breakdown risk.
- Overconsumption of resources can block a building, halting production. Resolve shortages before restarting operations.
- You can manually halt a building’s production if needed.
- Early-game strategy: prioritize food and wood production, followed by buildings that produce materials needed for construction or consumption.
- As your game progresses and the number of buildings and residents increases, the game may slow down because we didn't focus on optimizing it.

## Future Improvements

- find better sprites
- save and load a game
- add esthetic features like paths, decor (lake)
- add new types of buildings
- make days longer than a second
- show a preview of the size of a building

## Authors

- [Mathilde Chollon](https://github.com/mchlln)
- [Valentin Jonquière](https://github.com/Vjonquiere)
