package contracts

org.springframework.cloud.contract.spec.Contract.make {
    description "Should return validation error message, because start date of the campaign not set"
	request { // (1)
		method 'POST' // (2)
		url '/campaign' // (3)
        body([ // (4)
                campaign: [
                        'start.date': null,
                        'entity.a': [
                                'field.int': 9999
                        ],
                        'entity.b': [
                                'field.string': 'Hello world'
                        ]
                ]
        ])
		headers { // (5)
			contentType('application/json')
		}
	}
	response { // (6)
		status 200 // (7)
		body([ // (8)
               "status": 'FAILED',
			   "error.message": 'validation.failed'
		])
		headers { // (9)
			contentType('application/json')
		}
	}
}

/*
From the Consumer perspective, when shooting a request in the integration test:

(1) - If the consumer sends a request
(2) - With the "POST" method
(3) - to the URL "/campaign"
(4) - with the JSON body that
 * has a field `date` which not set
 * has a field `field.int` that is equal to '99999'
 * has a field `field.string` that is equal to 'Hello world'
(5) - with header 'Content-Type' equal to 'application/json'
(6) - then the response will be sent with
(7) - status equal '200'
(8) - and JSON body equal to
 { status: "FAILED", "error.message": 'validation.failed'}
(9) - with header 'Content-Type' equal to 'application/json'

From the Producer perspective, in the auto generated producer-side test:

(1) - A request will be sent to the producer
(2) - With the "POST" method
(3) - to the URL "/campaign"
(4) - with the JSON body that
 * has a field `date` which not set
 * has a field `field.int` that is equal to '99999'
 * has a field `field.string` that is equal to 'Hello world'
(5) - with header 'Content-Type' equal to 'application/json'
(6) - then the test will assert if the response has been sent with
(7) - status equal '200'
(8) - and JSON body equal to
 { status: "FAILED", "error.message": 'validation.failed'}
(9) - with header 'Content-Type' matching 'application/json.*'
 */

/*
Date Format ('yyyy-MM-ddd') Regular Expression Pattern
((19|20)[0-9]{2})-(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])')
(       #	start of group #1
((19|20)[0-9]{2})	#	    19[0-9][0-9] or 20[0-9][0-9]
)		#	end of group #1
-       #	follow by a "-"
(		#   start of group #2
0?[1-9]		#  01-09 or 1-9
        |           #  ..or
[12][0-9]		#  10-19 or 20-29
        |			#  ..or
3[01]			#  30, 31
) 		#   end of group #2
-		#   follow by a "-"
(		#    start of group #3
0?[1-9]		#	01-09 or 1-9
        |			#	..or
1[012]		#	10,11,12
)		#    end of group #3

*/
