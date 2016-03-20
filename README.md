# competence
A Confluence plug-in for managing the competences in the organization, in LinkedIn style with tag clouds.

Humans over square-shaped pegs measured in CV-project-month-kilograms. Generalism over specialism.

This project was born out of a goal to shift weight in evaluating competences and development goals of software teams from
CV-based project-month Excels of specialists towards human-shaped, peer-reviewed word clouds of people's competences
and interests. This shows a more organic and realistic image of people and teams on a glance
better than cumulative months in Excels.

Atlassian tools are de facto in the industry and it is a small hassle for the people to fill up and update their word clouds
on Confluence, as opposed to a yet another enterprise tool. Endorsing colleagues boosts workplace communication and
spirit.

This is work-in-progress. When complete, this will have:
* Management of teams, automatic generation of tag clouds per team and for the whole company.
* Navigating between tags and people, clicking a tag gives the list of people with that competence.
* Endorsement and suggestions of competence tags for colleagues.
* Competence tags are enforced to be Wikipedia article names, and have links to Wikipedia.

To get the initial content for the tag autocomplete, run the: `./getWikipediaArticles.sh`

Also run: `cd src/main/resources/js; npm install`

Install Atlassian SDK: https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project

Create Eclipse project files with: `atlas-mvn eclipse:eclipse`

Run with: `atlas-run` (If you get weird errors about required plugin missing, you can try
`-Datlassian.plugins.enable.wait=300`)

Take your browser to: `http://localhost:1990/confluence`

If it asks you to get an evaluation license, the build failed for some weird reason. Do `atlas-clean` and try again.

The credentials are: admin/admin

See also: [TODO.md](./TODO.md)

Screenshots
===========

![screenshot](./pics/confluence_tab.png "Screenshot")


