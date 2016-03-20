TODO:

* User profile page tab with a panel for managing competences for self and voting/suggesting new competences for others.
* REST API to GET competence information.
* Autocomplete for competence tags.
* Browsing competences, teams, people.
* Tag cloud.
* Drag-and-drop interface for creating and managing teams.

Views:

* User profile, WYSIWYG editing (system.profile menu item)
  * See suggested competences.
  * Auto-complete tag names.
  * Tag cloud for self.
  * List of all the teams you belong to.
* Full tag cloud, manage teams, drill down (widget)
  * Select a team (or the whole organization) -> Tag cloud for that team.
  * Select a competence -> List of people with that competence.
  * Select a person -> List of competences of that person for endorsement. Also tag cloud.
  * Drag-and-drop people to teams
  * Add / remove teams

Note:

* One person can belong to an arbitrary number of teams.
* No access control, anyone can edit teams.
* Competences are always Wikipedia article names, download the exhaustive list from here: http://dumps.wikimedia.org/enwiki/latest/enwiki-latest-all-titles-in-ns0.gz
  * Just run: `./getWikipediaArticles.sh`

See references:

* REST API: https://developer.atlassian.com/docs/atlassian-platform-common-components/rest-api-development/rest-plugin-module
* Active Objects: https://developer.atlassian.com/docs/atlassian-platform-common-components/active-objects/getting-started-with-active-objects
* system.profile: https://developer.atlassian.com/confdev/confluence-plugin-guide/confluence-plugin-module-types/web-ui-modules
* tag cloud: https://github.com/timdream/wordcloud2.js/blob/gh-pages/README.md
