package com.qa.repositorytest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Body;
import com.qa.persistence.repository.BodyDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class BodyRepoTest {

	private static final String MOCK_ARRAY1 = "[{\"posX\":100.0,\"posY\":50.0,\"velX\":5.0,\"velY\":3.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":10.0},{\"posX\":70.0,\"posY\":10.0,\"velX\":5.0,\"velY\":7.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":13.0},{\"posX\":20.0,\"posY\":30.0,\"velX\":2.0,\"velY\":11.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":15.0},{\"posX\":30.0,\"posY\":50.0,\"velX\":10.0,\"velY\":7.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":17.0}]";

	private static final String MOCK_OBJECT = "{\"posX\":100.0,\"posY\":50.0,\"velX\":5.0,\"velY\":3.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":10.0}";

	private static final String MOCK_BYTES = "/9j/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAAUCAH0AfQEASIAAhEBAxEBBCIA/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADgQBAAIRAxEEAAA/APn+iiigD5/ooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooooooAKKKKKKKKACiiiiiiigAooooopRjnJI44wKSnbS4BRVi1Szfz/tk88WImMPkwiTfJ/CrZZdqnnLDcR/dNV6KKKKQBRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFK2M/KSR7jFJRRTbu7gWL5LOO8kWwnnntRjZJPCInbgZyoZgOc/xH146VXoooooopAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRSnGBgnPfikoopt3AsTJZrZ2zQzzvdNu+0RvCFSPn5drBiXyOuVXHTnrVeiiiiiikAUUUUUUUUAFFFFFFFFABRRRS7jt25O3OcdqSiim23uBY+33n9nf2d9rn+w+b5/2bzD5fmY279vTdjjPXFV6KKKKKKQBRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFFFFFABRRRRRRRQAUUUUUUUUAFFFFf/Z";
	private Body b1;
	private Body b2;
	private Body b3;
	private Body b4;
	private List<Body> bodies;

	@InjectMocks
	private BodyDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	@Before
	public void setup() {
		b1 = new Body(100., 50., 5., 3., 10.);
		b2 = new Body(70., 10., 5., 7., 13.);
		b3 = new Body(20., 30., 2., 11., 15.);
		b4 = new Body(30., 50., 10., 7., 17.);
		bodies = new ArrayList<Body>();
		bodies.add(b1);
		bodies.add(b2);
		bodies.add(b3);
		bodies.add(b4);
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void getNextState() throws IOException {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(bodies);
		assertEquals(MOCK_BYTES, repo.getNextState(1));
	}

	@Test
	public void testGetAllBodys() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(bodies);
		assertEquals(MOCK_ARRAY1, repo.getAllBodies());
	}

	@Test
	public void testGetBody() {
		Mockito.when(manager.find(Mockito.any(), Mockito.anyInt())).thenReturn(bodies.get(0));
		assertEquals(MOCK_OBJECT, repo.getABody(1L));
	}

	@Test
	public void testCreateBody() {
		String reply = repo.createBody(MOCK_OBJECT);
		assertEquals("{\"message\": \"body has been sucessfully added\"}", reply);
	}

	@Test
	public void testDeleteBodyPass() {
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		String reply = repo.removeBody(1L);
		assertEquals("{\"message\": \"body has been sucessfully deleted\"}", reply);
	}

	@Test
	public void testDeleteBodyFail() {
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(false);
		String reply = repo.removeBody(0L);
		assertEquals("{\"message\": \"this body does not exist\"}", reply);
	}

	@Test
	public void testUpdateBodyFail() {
		String update = "{\"trainer\":\"Jordan\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(false);
		assertEquals("{\"message\": \"this body does not exist\"}", repo.updateBody(1L, update));
	}

	@Test
	public void testUpdateBodyPass() {
		String update = "{\"trainer\":\"Jordan\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		Mockito.when(manager.find(Mockito.any(), Mockito.anyLong())).thenReturn(bodies.get(0));
		assertEquals("{\"message\": \"body has been sucessfully updated\"}", repo.updateBody(1L, update));
	}
}