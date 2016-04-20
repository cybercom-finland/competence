TODO:

* Polymer
* A macro/panel for managing competences for self and voting/suggesting new competences for others.
* REST API competence and team information management.
* Browsing competences, teams, people.
* Drag-and-drop interface for creating and managing teams.
* User profile viewing by other users.
* Search for people/competences

Done:

* User profile page tab.
* Autocomplete for competence tags.
* Tag cloud.
* REST API.
* Persistence with Active Objects.
* Redis connection.

Views:

* WebItem: User profile editing, WYSIWYG editing (system.profile menu item)
  * See suggested competences.
  * Auto-complete tag names.
  * Tag cloud for self.
  * List of all the teams you belong to.
* Macro: Full tag cloud, manage teams, drill down (widget)
  * Select a team (or the whole organization) -> Tag cloud for that team.
  * Select a competence -> List of people with that competence.
  * Select a person -> List of competences of that person for endorsement. Also tag cloud.
  * Drag-and-drop people to teams
  * Add / remove teams
* User Profile view
  * This probably requires forking the default user profile plugin.
  
Note:

* One person can belong to an arbitrary number of teams.
* No access control, anyone can edit teams.
* Competences are always Wikipedia article names, download the exhaustive list from here: http://dumps.wikimedia.org/enwiki/latest/enwiki-latest-all-titles-in-ns0.gz
  * Just run: `./getWikipediaArticles.sh`
* Population of the autocomplete Redis database starts at the first autocomplete request, and takes some minutes. Words starting with 'A' work first.

See references:

* REST API: https://developer.atlassian.com/docs/atlassian-platform-common-components/rest-api-development/rest-plugin-module
* Active Objects: https://developer.atlassian.com/docs/atlassian-platform-common-components/active-objects/getting-started-with-active-objects
* system.profile: https://developer.atlassian.com/confdev/confluence-plugin-guide/confluence-plugin-module-types/web-ui-modules
* tag cloud: https://github.com/timdream/wordcloud2.js/blob/gh-pages/README.md
