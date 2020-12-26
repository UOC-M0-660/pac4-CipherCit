package edu.uoc.pac4.ui.streams

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uoc.pac4.R
import edu.uoc.pac4.data.streams.Stream
import edu.uoc.pac4.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_streams.*
import org.koin.android.viewmodel.ext.android.viewModel

class StreamsActivity : AppCompatActivity() {

    private val tag = "StreamsActivity"

    private val adapter = StreamsAdapter()
    private val layoutManager = LinearLayoutManager(this)

    private val viewModel: StreamsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streams)
        // Init RecyclerView
        initRecyclerView()
        // Swipe to Refresh Listener

        // set observer
        val streamsListObserver = Observer<List<Stream>> { newStreams ->
            // Update UI with Streams
            if (viewModel.nextCursor != null) {
                // We are adding more items to the list
                adapter.submitList(adapter.currentList.plus(newStreams))
            } else {
                // It's the first n items, no pagination yet
                adapter.submitList(newStreams)
            }

            // Show Error message to not leave the page empty
            if (newStreams.isEmpty()) {
                Toast.makeText(
                    this@StreamsActivity,
                    getString(R.string.error_streams), Toast.LENGTH_SHORT
                ).show()
            }
            // Hide Loading
            swipeRefreshLayout.isRefreshing = false
        }

        viewModel.streams.observe(this, streamsListObserver)

        swipeRefreshLayout.setOnRefreshListener {
            getStreams()
        }
        // Get Streams
        getStreams()
    }

    private fun initRecyclerView() {
        // Set Layout Manager
        recyclerView.layoutManager = layoutManager
        // Set Adapter
        recyclerView.adapter = adapter
        // Set Pagination Listener
        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                getStreams(viewModel.nextCursor)
            }

            override fun isLastPage(): Boolean {
                return viewModel.nextCursor == null
            }

            override fun isLoading(): Boolean {
                return swipeRefreshLayout.isRefreshing
            }
        })
    }

    //private var nextCursor: String? = null
    private fun getStreams(cursor: String? = null) {
        Log.d(tag, "Requesting streams with cursor $cursor")

        // Show Loading
        swipeRefreshLayout.isRefreshing = true

        viewModel.getStreams(cursor)
    }

    // region Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate Menu
        menuInflater.inflate(R.menu.menu_streams, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_user -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // endregion
}