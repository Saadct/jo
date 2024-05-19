# List des routes de l'api des jo

Selon les droits et les roles attribué certaines routes ne sont pas disponible pour tous

## Public

### Inscription
Pour s'inscrire.

*POST route :*
```
http://localhost:8080/auth/signup
```
body:

```
{
    "email": "test@gmail.com",
    "password": "test",
    "fullName": "efrei"
}
```
### Login

Pour se connecter .

*POST route :* 

```
http://localhost:8080/auth/login
```

body:

```
{
    "email": "test@gmail.com",
    "password": "test",
}
```

Par default toute les personnes qui s'inscrivent

### Evenement
Pour avoir une liste d'evenement .

*route GET*
```
http://localhost:8080/evenements
```

## User

Pour le role user voici les routes disponibles.
#### Ticket

*method POST*

acheter un ticket
```
http://localhost:8080/evenements/{eventId}/acheterTicket
```
reserver un ticket
```
http://localhost:8080/evenements/{eventId}/bookTicket
```

body:

```
{
    "name": "name test",
    "lastName": "lastname test"
}
```

*method PATCH*

payer un ticket reserver
```
http://localhost:8080/tickets/{ticketId}/paidBookTicket
```

*method POST*



acheter un lot de ticket
```
http://localhost:8080/evenements/{eventId}/acheterLotTicket
```

acheter un lot de ticket promotion
```
http://localhost:8080/evenements/{eventId}/acheterLotTicketPromo
```

body:

```
[
{
    "name": "name test",
    "lastName": "lastname test"
},
{
    "name": "name test 2",
    "lastName": "lastname test 2"
},
.
.
.
{
    "name": "name test n ",
    "lastName": "lastname test n"
}
]
```



