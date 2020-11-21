/*
 * calprint4.c
 *
 * 
 * Summer 2019.
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "emalloc.h"
#include "ics.h"
#include "listy.h"
#include <time.h>
#include <stdbool.h>
// #include <regex.h>
#define MAX_LINE_LEN 80

void print_event(node_t *n, void *arg) 
{
    assert(n != NULL);
    event_t *event = n->val;
	
	// clarify the event by whether event is repetitive or not
    if (strcmp(event->rrule, "") == 0) 
	{

         printf("EVENT: %s %s '%s' '%s'\n", event->dtstart,
            event->dtend, event->summary, event->location);
    }

	else 
	{
         printf("EVENT: %s %s '%s' '%s' '%s'\n", event->dtstart,
            event->dtend, event->summary, event->location,
            event->rrule);
    }
}


void dt_format(char *formatted_time, const char *dt_time, const int len)
{
    struct tm temp_time;
    time_t    full_time;
    char      temp[5];

    /*  
     * Ignore for now everything other than the year, month and date.
     * For conversion to work, months must be numbered from 0, and the 
     * year from 1900.
     */  
	 
	// fill a block of memory with the length of tm
    memset(&temp_time, 0, sizeof(struct tm));

	// read formatted data
    sscanf(dt_time, "%4d%2d%2d",
        &temp_time.tm_year, &temp_time.tm_mon, &temp_time.tm_mday);
    temp_time.tm_year -= 1900;
    temp_time.tm_mon -= 1;
    full_time = mktime(&temp_time);

	// use strftime to format data
    strftime(formatted_time, len, "%B %d, %Y (%a)", 
        localtime(&full_time));
}


/*
	This function is to increase the date
**/
void dt_increment(char *after, const char *before, int const num_days)
{
    struct tm temp_time, *p_temp_time;
    time_t    full_time;
    char      temp[5];

    memset(&temp_time, 0, sizeof(struct tm));
    sscanf(before, "%4d%2d%2d", &temp_time.tm_year,
        &temp_time.tm_mon, &temp_time.tm_mday);
    temp_time.tm_year -= 1900;
    temp_time.tm_mon -= 1;
    temp_time.tm_mday += num_days;

	// mktime is to convert time structure to time number
    full_time = mktime(&temp_time);
    after[0] = '\0';
/* Apparently there is an 9 required as the second parameter,
 * not an 8...  MZ
    strftime(after, 8, "%Y%m%d", localtime(&full_time));
*/
    strftime(after, 9, "%Y%m%d", localtime(&full_time));
    strncpy(after + 8, before + 8, MAX_LINE_LEN - 8); 
    after[MAX_LINE_LEN - 1] = '\0';
}



#ifdef DEBUG


void _demo() 
{
    event_t *temp_event = NULL;
    node_t  *temp_node  = NULL;
    node_t  *head = NULL;

    /* Add one event, without an RRULE */

    temp_event = emalloc(sizeof(event_t));
    strncpy(temp_event->dtstart, "20190706T120000", 17);
    strncpy(temp_event->dtend, "20190706T160000", 17);
    strncpy(temp_event->summary, "summary 1", SUMMARY_LEN);
    strncpy(temp_event->location, "location 1", LOCATION_LEN);
    temp_event->rrule[0] = '\0';
    temp_node = new_node(temp_event);
    head = add_front(head, temp_node);

    /* Add a second event, with an RRULE */

    temp_event = emalloc(sizeof(event_t));
    strncpy(temp_event->dtstart, "20190707T093000", 17);
    strncpy(temp_event->dtend, "20190707T102000", 17);
    strncpy(temp_event->summary, "uvwxyz 1234567", SUMMARY_LEN);
    strncpy(temp_event->location, "abcde 1234567", LOCATION_LEN);
    strncpy(temp_event->rrule, "yada RULE yada UNTIL yada", RRULE_LEN);
    temp_node = new_node(temp_event);
    head = add_front(head, temp_node);

    /* Print the list of events. */

    apply(head, print_event, NULL);

    /* Free up the memory. This is done rather deliberately
     * and manually. A full-featured function might better
     * serve for this. Asserts are liberally used here as they
     * express state that *must* be true if all of the code is
     * correctly working.
     */

    temp_node = head;
    assert(temp_node != NULL);
    head = remove_front(head);
    temp_event = temp_node->val;
    assert(temp_event != NULL);
    free(temp_event);
    free(temp_node);

    temp_node = head;
    assert(temp_node != NULL);
    head = remove_front(head);
    temp_event = temp_node->val;
    assert(temp_event != NULL);
    free(temp_event);
    free(temp_node);
   
    assert(head == NULL); 
    
}

#endif

/*
	This function is to read from provided filex
**/
node_t  * readfile(char *filename)
{

	FILE* stream = fopen(filename, "r");

	char *line =NULL;
	size_t len = 0;
	ssize_t read; 

	if(stream ==NULL){
		exit(EXIT_FAILURE);
	}

	event_t *temp_event = NULL;
    node_t  *temp_node  = NULL;
    node_t  *head = NULL;

	/*
		read each event startting from BEGIN:VEVENT. use strncmp 
		to extract the event information and store in structure
	**/

	while ((read = getline(&line, &len, stream)) != -1) 
	{
		if(read >= 12 && strncmp(line, "BEGIN:VEVENT", 12)==0)
		{
			// call emalloc 
			 temp_event = emalloc(sizeof(event_t));
		}

		// used strncpy to get the corresponding numbers
		else if(read>= 8 && strncmp(line, "DTSTART:", 8)==0)
		{
			strncpy(temp_event->dtstart, line+8, read-8-1);
		}
		else if(read>= 6 && strncmp(line, "DTEND:", 6)==0)
		{
			strncpy(temp_event->dtend, line+6, read-6-1);	
		}
		else if(read>= 6 && strncmp(line, "RRULE:", 6)==0)
		{
			 strncpy(temp_event->rrule, line+32, read-32-1-9);
		}
		else if(read>= 9 && strncmp(line, "LOCATION:", 9)==0)
		{
			 strncpy(temp_event->location, line+9, read-9-1);
		}
		else if(read>= 8 && strncmp(line, "SUMMARY:", 8)==0)
		{
			 strncpy(temp_event->summary, line+8, read-8-1);
		}
		
		// a single event ends by END:VEVENT
		else if(read >= 10 && strncmp(line, "END:VEVENT", 10)==0)
		{
			temp_node = new_node(temp_event);
   		     head = add_end(head, temp_node);
		}
		else
		{

		}
	}
	free(line);
	fclose(stream);
	assert(head !=NULL);
	return head;
}

/*
	This is function is built to expand the events
	under the RRULE. if RRULE seen we expand it to 
	multiple single events
**/
node_t * expand_event(node_t *header){
	
	node_t  *temp_head = NULL;
	node_t *curr = header;
	

	for ( ; curr != NULL; curr = curr->next) {

		if (strcmp(curr->val->rrule, "") != 0){

			// initialize strings for the use 
			char dtstart_before[MAX_LINE_LEN];
		    char dtstart_after[MAX_LINE_LEN];

		    char dtend_before[MAX_LINE_LEN];
		    char dtend_after[MAX_LINE_LEN];
		   
		    char output[MAX_LINE_LEN];

		    strcpy(dtstart_before, curr->val->dtstart);
		    strcpy(dtend_before, curr->val->dtend);

		    event_t *temp_event = NULL;
		
			// loop through the events which has rrule
		    while(strcmp(dtstart_before, curr->val->rrule)<=0 )
			{
		    	
		    	temp_event = emalloc(sizeof(event_t));

		    	strcpy(temp_event->dtstart, dtstart_before);
		    	strcpy(temp_event->dtend, dtend_before);
		    	strcpy(temp_event->location, curr->val->location);
		    	strcpy(temp_event->summary, curr->val->summary);
		    	strcpy(temp_event->rrule, "");

		    	dt_increment(dtstart_after, dtstart_before, 7);
		    	strcpy(dtstart_before, dtstart_after);

		    	dt_increment(dtend_after, dtend_before, 7);
		    	strcpy(dtend_before, dtend_after);

		    	node_t *temp_node = new_node(temp_event);
				temp_head = add_end(temp_head, temp_node);
		    }
			
		}
		else{
			node_t *temp_node = new_node(curr->val);
			temp_head = add_end(temp_head, temp_node);
		}
       
    }

    return temp_head;
}

/*
	This function is to sort events based on the time and summary info
**/
node_t *sort_events(node_t* head){
	node_t * outside= NULL;
	node_t * inside=NULL;

	outside  = head;

	if(head->next!=NULL)
	{// more than 1 events
		
		inside = head->next;
		for(; outside->next != NULL; outside = outside->next)

		{
			inside = outside->next;
			for(;inside!=NULL; inside = inside->next)
			{
				if(strcmp(outside->val->dtstart, inside->val->dtstart)>0)
				{
					event_t *temp = inside->val;
					inside->val = outside->val;
					outside->val  = temp; 
				}
			}
		}

		/* if events have all the same info except location. 
			Then we sort it based on location
		**/
		outside = head;
		for(;outside->next !=NULL; outside= outside->next)
		{
			int len_prev = strlen(outside->val->summary);
			int len_next = strlen(outside->next->val->summary);
			int r = strcmp(outside->val->dtstart, outside->next->val->dtstart);

			if( len_prev > len_next && strncmp(outside->val->summary, outside->next->val->summary, len_next)==0 && r==0)
			{
				event_t *temp = outside->next->val;
				outside->next->val = outside->val;
				outside->val  = temp; 
			}
		}
	}
	return head;
}

/*
	This function is to concatenate the data
**/
void convert(char result[],  int year, int month, int day, int flag )
{
	// initialize string date
	char date[20];
	
	sprintf(date, "%d", year);

	if(month<10)
	{
		strcat(date, "0");
	}

	char t1[4];
	sprintf(t1, "%d", month);
	strcat(date, t1);

	if(day<10)
	{
		strcat(date, "0");
	}

	char d1[4];
	sprintf(d1, "%d", day);
	strcat(date, d1);
	
	if(flag ==0)
	{ 
		strcat(date, "T000000");
	}

	else
	{
		strcat(date, "T235959");
	}
	strcpy(result, date);

}

/*
	This function is to display the output
**/

void display(node_t *head, int f_y, int f_m, int f_d, int t_y, int t_m, int t_d){

	node_t* curr = head;
	int s_year, s_month, s_day, s_hour, s_minute, s_second;
	int e_year, e_month, e_day, e_hour, e_minute, e_second;
	int p_year, p_month, p_day;
	
	// format data
	sscanf(head->val->dtstart, "%4d%2d%2dT%2d%2d%2d", &s_year,&s_month, &s_day, &s_hour, &s_minute, &s_second);
	sscanf(head->val->dtend, "%4d%2d%2dT%2d%2d%2d", &e_year,&e_month, &e_day, &e_hour, &e_minute, &e_second);
	int flag = 1;
	p_year = s_year;
	p_month = s_month;
	p_day = s_day;


    char from_start[17];
    char to_end[17];
	convert(from_start, f_y, f_m, f_d, 0);
	convert(to_end , t_y, t_m, t_d, 1);

	for(;curr!=NULL; curr = curr->next)
	{
		sscanf(curr->val->dtstart, "%4d%2d%2dT%2d%2d%2d", &s_year,&s_month, &s_day, &s_hour, &s_minute, &s_second);
		sscanf(curr->val->dtend, "%4d%2d%2dT%2d%2d%2d", &e_year,&e_month, &e_day, &e_hour, &e_minute, &e_second);
		 char curr_check[17];
		 convert(curr_check , s_year, s_month, s_day, 1);
		
		// if we match events 
		if( strcmp(from_start, curr_check)<=0 && strcmp(to_end, curr_check)>=0 )
		{

			if(s_year!= p_year || s_month != p_month || s_day != p_day)
			{
				flag = 1;
				char previous[17];
				convert(previous, p_year, p_month,p_day, 0);
				
				// print a new line if all the event in a same day over, otherwise do nothing
				if(strcmp(previous, from_start)<=0 && strcmp(curr_check, from_start)>=0)
				{
					

				}
				else
					printf("\n");
				
			}

			// otherwise set flag to zero
			else
			{
				if(curr != head)
					flag = 0;
			}

			// format and print dashes only event seen
			if(flag==1)
			{
				char dt[MAX_LINE_LEN];
				dt_format(dt,curr->val->dtstart , MAX_LINE_LEN);
				printf("%s\n",  dt);

				// print all dashes
				for(int i=0;i<strlen(dt);i++)
					printf("%s","-" );
				
				printf("\n");

			}
			
			// convert to the time under am or pm and print out
			if(s_hour>12)
				printf("%2d:%02d pm to ",s_hour-12, s_minute);
			
			else if(s_hour==12)
				printf("%2d:%02d pm to ",s_hour, s_minute);
			
			else
				printf("%2d:%02d am to ",s_hour, s_minute);
			
			if(e_hour>12)
				printf("%2d:%02d pm: ",e_hour-12, e_minute);
			
			else if(e_hour==12)
				printf("%2d:%02d pm: ",e_hour, e_minute);
			
			else
				printf("%2d:%02d am: ",e_hour, e_minute);
			
			// print out rest of information(summary and location)
			printf("%s [%s]", curr->val->summary, curr->val->location );
			printf("\n");

		}
		p_year = s_year;
		p_month = s_month;
		p_day = s_day;
	}
}


int main(int argc, char *argv[])
{

    int from_y = 0, from_m = 0, from_d = 0;
    int to_y = 0, to_m = 0, to_d = 0;
    char *filename = NULL;
    int i; 

    for (i = 0; i < argc; i++) 
	{
        if (strncmp(argv[i], "--start=", 7) == 0) 
		{
            sscanf(argv[i], "--start=%d/%d/%d", &from_d, &from_m, &from_y);
        } 
		
		else if (strncmp(argv[i], "--end=", 5) == 0) 
		{
            sscanf(argv[i], "--end=%d/%d/%d", &to_d, &to_m, &to_y);
        } 
		
		else if (strncmp(argv[i], "--file=", 7) == 0) 
		{
            filename = argv[i]+7;
        }
    }

    if (from_y == 0 || to_y == 0 || filename == NULL) 
	{
        fprintf(stderr, 
            "usage: %s --start=dd/mm/yyyy --end=dd/mm/yyyy --file=icsfile\n",
            argv[0]);
        exit(1);
    }

    node_t *head = readfile(filename);
    head = expand_event(head);
    head = sort_events(head);
    display(head, from_y, from_m, from_d, to_y, to_m, to_d);

    // apply(head, print_event, NULL);
    // printf("\n\n\n");

   
/* 
 * Showing some simple usage of the linked-list routines.
 */

#ifdef DEBUG
    _demo();
#endif

    exit(0);
}
